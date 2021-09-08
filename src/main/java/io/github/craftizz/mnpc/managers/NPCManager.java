package io.github.craftizz.mnpc.managers;

import io.github.craftizz.mnpc.MNPC;
import io.github.craftizz.mnpc.npc.Page;
import io.github.craftizz.mnpc.npc.SocialNPC;
import io.github.craftizz.mnpc.npc.UserData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class NPCManager {

    private final HashMap<Integer, SocialNPC> npcMap;
    private final HashMap<UUID, UserData> userMap;

    public NPCManager() {
        this.npcMap = new HashMap<>();
        this.userMap = new HashMap<>();
    }

    public void addSocialNPC(final @NotNull SocialNPC npc) {
        npcMap.put(npc.getNpcId(), npc);
    }

    public void addPlayerData(final @NotNull UserData userData) {
        userMap.put(userData.getUniqueId(), userData);
    }

    public void handleSocialNPC(final @NotNull Player player,
                                final int npcId) {

        final SocialNPC socialNPC = npcMap.get(npcId);

        if (socialNPC == null) {
            return;
        }

        if (!socialNPC.meetsRequirement(player)) {
            return;
        }

        final UserData userData = getPlayerData(player);
        final Integer pageData = userData.getPageData(npcId);

        final Page page;

        if (pageData == null) {
            page = socialNPC.getFirstClickPage();
            userData.setPageData(npcId, 0);
        } else {
            page = socialNPC.getPage(pageData);
            userData.setPageData(npcId, pageData + 1);
        }

        if (page != null) {
            page.play(player);
        }

    }

    public UserData getPlayerData(final @NotNull Player player) {
        UserData userData = userMap.get(player.getUniqueId());

        if (userData != null) {
            return userData;
        }

        userData = new UserData(player.getUniqueId());
        userMap.put(player.getUniqueId(), userData);

        return userData;
    }

    public HashMap<Integer, SocialNPC> getNpcMap() {
        return npcMap;
    }

    public void clearNPCs() {
        npcMap.clear();
    }

}
