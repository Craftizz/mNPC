package io.github.craftizz.mnpc.npc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public class UserData {

    private final UUID uniqueId;
    private final HashMap<Integer, Integer> pageData;

    public UserData(final @NotNull UUID uniqueId) {
        this.uniqueId = uniqueId;
        this.pageData = new HashMap<>();
    }

    public void setPageData(final int npcId,
                            final int page) {
        pageData.put(npcId, page);
    }

    public @Nullable Integer getPageData(final int npcId) {
        return pageData.get(npcId);
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
