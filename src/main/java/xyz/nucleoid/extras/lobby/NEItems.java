package xyz.nucleoid.extras.lobby;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.nucleoid.extras.NucleoidExtras;
import xyz.nucleoid.extras.NucleoidExtrasConfig;
import xyz.nucleoid.extras.lobby.block.tater.TinyPotatoBlock;
import xyz.nucleoid.extras.lobby.item.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class NEItems {
    public static final ItemGroup ITEM_GROUP = PolymerItemGroupUtils.builder(NucleoidExtras.identifier("general"))
        .displayName(Text.translatable("text.lobby_extras.name"))
        .icon(() -> new ItemStack(NEItems.BOTANICAL_TINY_POTATO))
        .entries((context, entries) -> {
            entries.add(NEItems.QUICK_ARMOR_STAND);
            entries.add(NEItems.END_PORTAL);
            entries.add(NEItems.END_GATEWAY);
            entries.add(NEItems.SAFE_TNT);
            entries.add(NEItems.LAUNCH_FEATHER);
            entries.add(NEItems.GOLD_LAUNCH_PAD);
            entries.add(NEItems.IRON_LAUNCH_PAD);
            entries.add(NEItems.INFINITE_DISPENSER);
            entries.add(NEItems.INFINITE_DROPPER);
            entries.add(NEItems.SNAKE_BLOCK);
            entries.add(NEItems.FAST_SNAKE_BLOCK);
            entries.add(NEItems.BLACK_CONCRETE_POWDER);
            entries.add(NEItems.BLUE_CONCRETE_POWDER);
            entries.add(NEItems.BROWN_CONCRETE_POWDER);
            entries.add(NEItems.CYAN_CONCRETE_POWDER);
            entries.add(NEItems.GREEN_CONCRETE_POWDER);
            entries.add(NEItems.GRAY_CONCRETE_POWDER);
            entries.add(NEItems.LIGHT_BLUE_CONCRETE_POWDER);
            entries.add(NEItems.LIGHT_GRAY_CONCRETE_POWDER);
            entries.add(NEItems.LIME_CONCRETE_POWDER);
            entries.add(NEItems.MAGENTA_CONCRETE_POWDER);
            entries.add(NEItems.ORANGE_CONCRETE_POWDER);
            entries.add(NEItems.PINK_CONCRETE_POWDER);
            entries.add(NEItems.PURPLE_CONCRETE_POWDER);
            entries.add(NEItems.RED_CONCRETE_POWDER);
            entries.add(NEItems.WHITE_CONCRETE_POWDER);
            entries.add(NEItems.YELLOW_CONCRETE_POWDER);
            entries.add(NEItems.GAME_PORTAL_OPENER);
            entries.add(NEItems.BOTANICAL_TINY_POTATO);
        })
        .build();


    public static final Item END_PORTAL = createSimple(NEBlocks.END_PORTAL, Items.BLACK_CARPET);
    public static final Item END_GATEWAY = createSimple(NEBlocks.END_GATEWAY, Items.BLACK_WOOL);
    public static final Item SAFE_TNT = createSimple(NEBlocks.SAFE_TNT, Items.TNT);

    public static final Item GOLD_LAUNCH_PAD = createSimple(NEBlocks.GOLD_LAUNCH_PAD, Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
    public static final Item IRON_LAUNCH_PAD = createSimple(NEBlocks.IRON_LAUNCH_PAD, Items.HEAVY_WEIGHTED_PRESSURE_PLATE);


    public static final Item INFINITE_DISPENSER = createSimple(NEBlocks.INFINITE_DISPENSER, Items.DISPENSER);
    public static final Item INFINITE_DROPPER = createSimple(NEBlocks.INFINITE_DROPPER, Items.DROPPER);
    public static final Item SNAKE_BLOCK = createSimple(NEBlocks.SNAKE_BLOCK, Items.LIME_CONCRETE);
    public static final Item FAST_SNAKE_BLOCK = createSimple(NEBlocks.FAST_SNAKE_BLOCK, Items.LIGHT_BLUE_CONCRETE);

    public static final Item BLACK_CONCRETE_POWDER = createSimple(NEBlocks.BLACK_CONCRETE_POWDER, Items.BLACK_CONCRETE_POWDER);
    public static final Item BLUE_CONCRETE_POWDER = createSimple(NEBlocks.BLUE_CONCRETE_POWDER, Items.BLUE_CONCRETE_POWDER);
    public static final Item BROWN_CONCRETE_POWDER = createSimple(NEBlocks.BROWN_CONCRETE_POWDER, Items.BROWN_CONCRETE_POWDER);
    public static final Item CYAN_CONCRETE_POWDER = createSimple(NEBlocks.CYAN_CONCRETE_POWDER, Items.CYAN_CONCRETE_POWDER);
    public static final Item GREEN_CONCRETE_POWDER = createSimple(NEBlocks.GREEN_CONCRETE_POWDER, Items.GREEN_CONCRETE_POWDER);
    public static final Item GRAY_CONCRETE_POWDER = createSimple(NEBlocks.GRAY_CONCRETE_POWDER, Items.GRAY_CONCRETE_POWDER);
    public static final Item LIGHT_BLUE_CONCRETE_POWDER = createSimple(NEBlocks.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_CONCRETE_POWDER);
    public static final Item LIGHT_GRAY_CONCRETE_POWDER = createSimple(NEBlocks.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_CONCRETE_POWDER);
    public static final Item LIME_CONCRETE_POWDER = createSimple(NEBlocks.LIME_CONCRETE_POWDER, Items.LIME_CONCRETE_POWDER);
    public static final Item MAGENTA_CONCRETE_POWDER = createSimple(NEBlocks.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_CONCRETE_POWDER);
    public static final Item ORANGE_CONCRETE_POWDER = createSimple(NEBlocks.ORANGE_CONCRETE_POWDER, Items.ORANGE_CONCRETE_POWDER);
    public static final Item PINK_CONCRETE_POWDER = createSimple(NEBlocks.PINK_CONCRETE_POWDER, Items.PINK_CONCRETE_POWDER);
    public static final Item PURPLE_CONCRETE_POWDER = createSimple(NEBlocks.PURPLE_CONCRETE_POWDER, Items.PURPLE_CONCRETE_POWDER);
    public static final Item RED_CONCRETE_POWDER = createSimple(NEBlocks.RED_CONCRETE_POWDER, Items.RED_CONCRETE_POWDER);
    public static final Item WHITE_CONCRETE_POWDER = createSimple(NEBlocks.WHITE_CONCRETE_POWDER, Items.WHITE_CONCRETE_POWDER);
    public static final Item YELLOW_CONCRETE_POWDER = createSimple(NEBlocks.YELLOW_CONCRETE_POWDER, Items.YELLOW_CONCRETE_POWDER);

    public static final Item BOTANICAL_TINY_POTATO = createHead(NEBlocks.BOTANICAL_TINY_POTATO);

    public static final Item QUICK_ARMOR_STAND = new QuickArmorStandItem(new Item.Settings());
    public static final Item GAME_PORTAL_OPENER = new GamePortalOpenerItem(new Item.Settings().maxCount(1));
    public static final Item LAUNCH_FEATHER = new LaunchFeatherItem(new Item.Settings().maxCount(1));

    private static Item createHead(Block head) {
        if (head instanceof TinyPotatoBlock tinyPotatoBlock) {
            return new LobbyHeadItem(head, new Item.Settings(), tinyPotatoBlock.getItemTexture());
        } else if (head instanceof PolymerHeadBlock headBlock) {
            return new LobbyHeadItem(head, new Item.Settings(), headBlock.getPolymerSkinValue(head.getDefaultState(), BlockPos.ORIGIN, null));
        }

        return createSimple(head, Items.STONE);
    }

    private static Item createSimple(Block block, Item virtual) {
        return new LobbyBlockItem(block, new Item.Settings(), virtual);
    }

    public static void register() {
        register("end_portal", END_PORTAL);
        register("end_gateway", END_GATEWAY);
        register("safe_tnt", SAFE_TNT);
        register("gold_launch_pad", GOLD_LAUNCH_PAD);
        register("iron_launch_pad", IRON_LAUNCH_PAD);
        register("infinite_dispenser", INFINITE_DISPENSER);
        register("infinite_dropper", INFINITE_DROPPER);
        register("snake_block", SNAKE_BLOCK);
        register("fast_snake_block", FAST_SNAKE_BLOCK);

        register("black_concrete_powder", BLACK_CONCRETE_POWDER);
        register("blue_concrete_powder", BLUE_CONCRETE_POWDER);
        register("brown_concrete_powder", BROWN_CONCRETE_POWDER);
        register("cyan_concrete_powder", CYAN_CONCRETE_POWDER);
        register("green_concrete_powder", GREEN_CONCRETE_POWDER);
        register("gray_concrete_powder", GRAY_CONCRETE_POWDER);
        register("light_blue_concrete_powder", LIGHT_BLUE_CONCRETE_POWDER);
        register("light_gray_concrete_powder", LIGHT_GRAY_CONCRETE_POWDER);
        register("lime_concrete_powder", LIME_CONCRETE_POWDER);
        register("magenta_concrete_powder", MAGENTA_CONCRETE_POWDER);
        register("orange_concrete_powder", ORANGE_CONCRETE_POWDER);
        register("pink_concrete_powder", PINK_CONCRETE_POWDER);
        register("purple_concrete_powder", PURPLE_CONCRETE_POWDER);
        register("red_concrete_powder", RED_CONCRETE_POWDER);
        register("white_concrete_powder", WHITE_CONCRETE_POWDER);
        register("yellow_concrete_powder", YELLOW_CONCRETE_POWDER);

        registerTater("tine_potato", BOTANICAL_TINY_POTATO);
        register("quick_armor_stand", QUICK_ARMOR_STAND);
        register("game_portal_opener", GAME_PORTAL_OPENER);
        register("launch_feather", LAUNCH_FEATHER);

        ServerPlayConnectionEvents.JOIN.register(NEItems::onPlayerJoin);
    }

    private static boolean tryOfferStack(ServerPlayNetworkHandler handler, Item item, Consumer<ItemStack> consumer) {
        var inventory = handler.getPlayer().getInventory();

        if (inventory.containsAny(Collections.singleton(item))) {
            return false;
        }

        var stack = new ItemStack(item);
        consumer.accept(stack);

        handler.getPlayer().getInventory().offer(stack, true);
        return true;
    }

    private static boolean tryOfferStack(ServerPlayNetworkHandler handler, Item item) {
        return tryOfferStack(handler, item, stack -> {});
    }

    private static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender packetSender, MinecraftServer server) {
        var config = NucleoidExtrasConfig.get();

        config.gamePortalOpener().ifPresent(gamePortal -> {
            tryOfferStack(handler, GAME_PORTAL_OPENER, stack -> {
                GamePortalOpenerItem.setGamePortalId(stack, gamePortal);
            });
        });
    }

    private static <T extends Item> T registerTater(String id, T item) {
        register(id, item);
        return item;
    }

    private static <T extends Item> T register(String id, T item) {
        return Registry.register(Registries.ITEM, NucleoidExtras.identifier(id), item);
    }
}
