package ru.pinkgoosik.winterly.fabric.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.pinkgoosik.winterly.config.WinterlyClientConfig;

@Environment(EnvType.CLIENT)
public class WinterlyModMenuIntegration implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return WinterlyClientConfig::buildScreen;
	}

}
