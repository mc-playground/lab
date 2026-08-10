package org.rlaxogh76.lab.commands;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.rlaxogh76.lab.Lab;

public class MyInventory implements InventoryHolder { // 인벤토리 홀더를 구현하는 클래스

    private final Inventory inventory; // 인벤토리 객체를 저장하는 변수

    private int clicks = 0; // 돌 클릭 횟수를 저장하는 변수

    public MyInventory(Lab plugin) {
        this.inventory = plugin.getServer().createInventory(this, 9); // 인벤토리 생성

        this.inventory.setItem(0, ItemStack.of(Material.GREEN_CONCRETE)); // 0번 자리에 초록색 콘크리트 아이템 설정
        this.inventory.setItem(8, ItemStack.of(Material.RED_CONCRETE)); // 8번 자리에 빨간색 콘크리트 아이템 설정
    }

    // 돌 클릭 횟수를 증가시키는 메서드
    public void addClick() {
        this.clicks++;
        this.updateCounter();
    }

    // 돌 클릭 횟수를 감소시키는 메서드
    public void removeClick() {
        if (this.clicks > 0) {
            this.clicks--;
            this.updateCounter();
        }
    }

    // 인벤토리의 5번 자리에 클릭 횟수를 표시하는 메서드
    private void updateCounter() {
        this.inventory.setItem(4, ItemStack.of(Material.BEDROCK, this.clicks)); // 클릭할 때 마다 4번 자리에 베드락 블록 생성
    }

    @Override
    public Inventory getInventory() { // InventoryHolder 인터페이스의 메서드 구현
        return this.inventory; // 인벤토리 반환
    }
}