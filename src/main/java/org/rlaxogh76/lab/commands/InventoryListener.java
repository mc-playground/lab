package org.rlaxogh76.lab.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof MyInventory myInventory)) {
            return;
        }

        event.setCancelled(true); // 아이템을 실제로 집어가지 못하게 막음

        if (event.getSlot() == 0) { // 0번 슬롯(초록색 콘크리트)을 클릭했을 때
            myInventory.addClick();
        } else if (event.getSlot() == 8) { // 8번 슬롯(빨간색 콘크리트)을 클릭했을 때
            myInventory.removeClick();
        }
    }
}