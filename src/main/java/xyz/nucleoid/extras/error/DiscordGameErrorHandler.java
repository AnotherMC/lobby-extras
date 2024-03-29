package xyz.nucleoid.extras.error;

import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public final class DiscordGameErrorHandler implements AutoCloseable {
    private static final int MAX_CHARACTER_LIMIT = 2000;

    private final DiscordWebhook webhook;
    private final String source;

    private final List<ReportedError> reportedErrors = new ArrayList<>();

    public DiscordGameErrorHandler(DiscordWebhook webhook, String source) {
        this.webhook = webhook;
        this.source = source;
    }

    public void report(Throwable throwable, @Nullable String context) {
        this.reportedErrors.add(new ReportedError(throwable, context));
    }

    @Override
    public void close() {
        if (this.reportedErrors.isEmpty()) {
            return;
        }

        int displayCount = Math.min(this.reportedErrors.size(), 20);
        var errors = this.reportedErrors.subList(0, displayCount);

        var content = new StringBuilder();
        content.append(":warning: Reporting ").append(errors.size()).append(" errors from: **").append(this.source).append("** :warning:\n");

        if (errors.size() < this.reportedErrors.size()) {
            content.append("_Skipping ").append(this.reportedErrors.size() - errors.size()).append(" errors..._\n");
        }

        content.append('\n');

        var errorsContent = this.generateErrorsContent(errors, true);

        if (content.length() + errorsContent.length() < MAX_CHARACTER_LIMIT) {
            content.append(errorsContent);
            this.webhook.post(new DiscordWebhook.Message(content.toString()));
        } else {
            content.append("Traces have been attached.");

            errorsContent = this.generateErrorsContent(errors, false);
            this.webhook.post(new DiscordWebhook.Message(content.toString()).addFile("trace.txt", errorsContent));
        }
    }

    private String generateErrorsContent(List<ReportedError> errors, boolean message) {
        var errorsContent = new StringBuilder();

        for (var error : errors) {
            var traceWriter = new StringWriter();
            error.cause.printStackTrace(new PrintWriter(traceWriter));

            if (message) {
                if (error.context != null) {
                    errorsContent.append("**").append(error.context).append("**\n");
                }

                errorsContent.append("```\n");
                errorsContent.append(traceWriter);
                errorsContent.append("```\n");
            } else {
                if (error.context != null) {
                    errorsContent.append(error.context).append(":\n\n");
                }
                errorsContent.append(traceWriter);
            }
        }

        return errorsContent.toString();
    }

    private record ReportedError(Throwable cause, String context) {
    }
}
