/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.client.gui;

import blusunrize.immersiveengineering.client.ClientUtils;
import blusunrize.immersiveengineering.common.blocks.metal.RefineryTileEntity;
import blusunrize.immersiveengineering.common.gui.RefineryContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;

public class RefineryScreen extends IEContainerScreen
{
	RefineryTileEntity tile;

	public RefineryScreen(PlayerInventory inventoryPlayer, RefineryTileEntity tile)
	{
		super(new RefineryContainer(inventoryPlayer, tile), inventoryPlayer);
		this.tile = tile;
	}

	@Override
	public void render(int mx, int my, float partial)
	{
		super.render(mx, my, partial);
		ArrayList<ITextComponent> tooltip = new ArrayList<>();
		ClientUtils.handleGuiTank(tile.tanks[0], guiLeft+13, guiTop+20, 16, 47, 177, 31, 20, 51, mx, my, "immersiveengineering:textures/gui/refinery.png", tooltip);
		ClientUtils.handleGuiTank(tile.tanks[1], guiLeft+61, guiTop+20, 16, 47, 177, 31, 20, 51, mx, my, "immersiveengineering:textures/gui/refinery.png", tooltip);
		ClientUtils.handleGuiTank(tile.tanks[2], guiLeft+109, guiTop+20, 16, 47, 177, 31, 20, 51, mx, my, "immersiveengineering:textures/gui/refinery.png", tooltip);
		if(mx > guiLeft+157&&mx < guiLeft+164&&my > guiTop+21&&my < guiTop+67)
			tooltip.add(new StringTextComponent(tile.getEnergyStored(null)+"/"+tile.getMaxEnergyStored(null)+" IF"));

		if(!tooltip.isEmpty())
		{
			ClientUtils.drawHoveringText(tooltip, mx, my, font, guiLeft+xSize, -1);
			RenderHelper.enableGUIStandardItemLighting();
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mx, int my)
	{
		GlStateManager.color3f(1.0F, 1.0F, 1.0F);
		ClientUtils.bindTexture("immersiveengineering:textures/gui/refinery.png");
		this.blit(guiLeft, guiTop, 0, 0, xSize, ySize);

		//		if(tile.tick>0)
		//		{
		//			int h = (int)(18*(tile.tick/80f));
		//			ClientUtils.drawGradientRect(guiLeft+83,guiTop+34+h, guiLeft+90,guiTop+52, 0xffd4d2ab, 0xffc4c29e);
		//		}

		int stored = (int)(46*(tile.getEnergyStored(null)/(float)tile.getMaxEnergyStored(null)));
		ClientUtils.drawGradientRect(guiLeft+157, guiTop+21+(46-stored), guiLeft+164, guiTop+67, 0xffb51500, 0xff600b00);

		ClientUtils.handleGuiTank(tile.tanks[0], guiLeft+13, guiTop+20, 16, 47, 177, 31, 20, 51, mx, my, "immersiveengineering:textures/gui/refinery.png", null);
		ClientUtils.handleGuiTank(tile.tanks[1], guiLeft+61, guiTop+20, 16, 47, 177, 31, 20, 51, mx, my, "immersiveengineering:textures/gui/refinery.png", null);
		ClientUtils.handleGuiTank(tile.tanks[2], guiLeft+109, guiTop+20, 16, 47, 177, 31, 20, 51, mx, my, "immersiveengineering:textures/gui/refinery.png", null);


		//		if(tile.tank.getFluid()!=null && tile.tank.getFluid().getFluid()!=null)
		//		{
		//			int h = (int)(47*(tile.tank.getFluid().amount/(float)tile.tank.getCapacity()));
		//			ClientUtils.drawRepeatedFluidIcon(tile.tank.getFluid().getFluid(), guiLeft+98,guiTop+21+47-h, 16, h);
		//			ClientUtils.bindTexture("immersiveengineering:textures/gui/fluidProducer.png");
		//		}
		//		this.blit(guiLeft+96,guiTop+19, 177,31, 20,51);
	}
}