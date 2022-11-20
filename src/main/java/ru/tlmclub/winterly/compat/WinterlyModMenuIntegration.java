package ru.tlmclub.winterly.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.tlmclub.winterly.config.WinterlyClothConfig;

@Environment(EnvType.CLIENT)
public class WinterlyModMenuIntegration implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return WinterlyClothConfig::buildScreen;
	}

}
