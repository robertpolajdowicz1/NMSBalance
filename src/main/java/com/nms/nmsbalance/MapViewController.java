package com.nms.nmsbalance;

import com.nms.nmsbalance.services.Services;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;

public class MapViewController {
    private Services services;
    private HashMap<Integer,Label> labelHashMap = new HashMap<>();
    private HashMap<Integer,Label> labelValueHashmap = new HashMap<>();
    @FXML
    private Button refreshButton;
    @FXML
    private Label r1F, r1D, r1P, r1A,r2F, r2D, r2P, r2A,r3F, r3D, r3P, r3A,r4F, r4D, r4P, r4A,r5F, r5D, r5P, r5A,r6F, r6D, r6P,
            r6A,r7F, r7D, r7P, r7A,r8F, r8D, r8P, r8A,r9F, r9D, r9P, r9A,r10F, r10D, r10P, r10A,r11F, r11D, r11P, r11A,
            r12F, r12D, r12P, r12A,r13F, r13D, r13P, r13A,r14F, r14D, r14P, r14A,r15F, r15D, r15P, r15A,r16F, r16D, r16P,
            r16A,r17F, r17D, r17P, r17A,r18F, r18D, r18P, r18A,r19F, r19D, r19P, r19A,r20F, r20D, r20P, r20A,r21F, r21D,
            r21P, r21A;
    @FXML
    private Label  r1V,r2V, r3V, r4V, r5V, r6V, r7V, r8V, r9V, r10V, r11V, r12V, r13V, r14V, r15V, r16V, r17V, r18V, r19V, r20V, r21V;


    public void setServices(Services services) {
        this.services = services;
        labelHashMap.put(1, r1F);
        labelHashMap.put(2, r1D);
        labelHashMap.put(3, r1P);
        labelHashMap.put(4, r1A);

        labelHashMap.put(5, r2F);
        labelHashMap.put(6, r2D);
        labelHashMap.put(7, r2P);
        labelHashMap.put(8, r2A);

        labelHashMap.put(9, r3F);
        labelHashMap.put(10, r3D);
        labelHashMap.put(11, r3P);
        labelHashMap.put(12, r3A);

        labelHashMap.put(13, r4F);
        labelHashMap.put(14, r4D);
        labelHashMap.put(15, r4P);
        labelHashMap.put(16, r4A);

        labelHashMap.put(17, r5F);
        labelHashMap.put(18, r5D);
        labelHashMap.put(19, r5P);
        labelHashMap.put(20, r5A);

        labelHashMap.put(21, r6F);
        labelHashMap.put(22, r6D);
        labelHashMap.put(23, r6P);
        labelHashMap.put(24, r6A);

        labelHashMap.put(25, r7F);
        labelHashMap.put(26, r7D);
        labelHashMap.put(27, r7P);
        labelHashMap.put(28, r7A);

        labelHashMap.put(29, r8F);
        labelHashMap.put(30, r8D);
        labelHashMap.put(31, r8P);
        labelHashMap.put(32, r8A);

        labelHashMap.put(33, r9F);
        labelHashMap.put(34, r9D);
        labelHashMap.put(35, r9P);
        labelHashMap.put(36, r9A);

        labelHashMap.put(37, r10F);
        labelHashMap.put(38, r10D);
        labelHashMap.put(39, r10P);
        labelHashMap.put(40, r10A);

        labelHashMap.put(41, r11F);
        labelHashMap.put(42, r11D);
        labelHashMap.put(43, r11P);
        labelHashMap.put(44, r11A);

        labelHashMap.put(45, r12F);
        labelHashMap.put(46, r12D);
        labelHashMap.put(47, r12P);
        labelHashMap.put(48, r12A);

        labelHashMap.put(49, r13F);
        labelHashMap.put(50, r13D);
        labelHashMap.put(51, r13P);
        labelHashMap.put(52, r13A);

        labelHashMap.put(53, r14F);
        labelHashMap.put(54, r14D);
        labelHashMap.put(55, r14P);
        labelHashMap.put(56, r14A);

        labelHashMap.put(57, r15F);
        labelHashMap.put(58, r15D);
        labelHashMap.put(59, r15P);
        labelHashMap.put(60, r15A);

        labelHashMap.put(61, r16F);
        labelHashMap.put(62, r16D);
        labelHashMap.put(63, r16P);
        labelHashMap.put(64, r16A);

        labelHashMap.put(65, r17F);
        labelHashMap.put(66, r17D);
        labelHashMap.put(67, r17P);
        labelHashMap.put(68, r17A);

        labelHashMap.put(69, r18F);
        labelHashMap.put(70, r18D);
        labelHashMap.put(71, r18P);
        labelHashMap.put(72, r18A);

        labelHashMap.put(73, r19F);
        labelHashMap.put(74, r19D);
        labelHashMap.put(75, r19P);
        labelHashMap.put(76, r19A);

        labelHashMap.put(77, r20F);
        labelHashMap.put(78, r20D);
        labelHashMap.put(79, r20P);
        labelHashMap.put(80, r20A);

        labelHashMap.put(81, r21F);
        labelHashMap.put(82, r21D);
        labelHashMap.put(83, r21P);
        labelHashMap.put(84, r21A);

        labelValueHashmap.put(1, r1V);
        labelValueHashmap.put(2, r2V);
        labelValueHashmap.put(3, r3V);
        labelValueHashmap.put(4, r4V);
        labelValueHashmap.put(5, r5V);
        labelValueHashmap.put(6, r6V);
        labelValueHashmap.put(7, r7V);
        labelValueHashmap.put(8, r8V);
        labelValueHashmap.put(9, r9V);
        labelValueHashmap.put(10, r10V);
        labelValueHashmap.put(11, r11V);
        labelValueHashmap.put(12, r12V);
        labelValueHashmap.put(13, r13V);
        labelValueHashmap.put(14, r14V);
        labelValueHashmap.put(15, r15V);
        labelValueHashmap.put(16, r16V);
        labelValueHashmap.put(17, r17V);
        labelValueHashmap.put(18, r18V);
        labelValueHashmap.put(19, r19V);
        labelValueHashmap.put(20, r20V);
        labelValueHashmap.put(21, r21V);
    }

    public void onRefreshButtonClicked() {
        for (int i = 1; i < 22 ; i++) {
            labelHashMap.get(i*4-3).setVisible(services.getShip().checkFireStatus(i));
            labelHashMap.get(i*4-2).setVisible(services.getShip().checkDamageStatus(i));
            labelHashMap.get(i*4-1).setVisible(services.getShip().checkPlayerInsideStatus(i));
            labelHashMap.get(i*4).setVisible(services.getShip().checkAlienInsideStatus(i));
            labelValueHashmap.get(i).setText(String.valueOf(services.getShip().getRoomValue(i)+services.getShip().getInfluenceValue(i)));
        }
    }
}
