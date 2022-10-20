/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.framework.options;

import java.awt.Color;
import java.beans.PropertyEditor;

import generic.theme.GColor;
import generic.theme.Gui;
import ghidra.util.HelpLocation;

/**
 * Options implementation for theme color options. A ThemeColorOption is an option that, when
 * changed, affects the current theme and is saved in the theme, instead of being saved with
 * normal non-theme related options.
 */
public class ThemeColorOption extends Option {

	private String colorId;

	public ThemeColorOption(String optionName, String colorId, String description,
			HelpLocation help, PropertyEditor editor) {
		super(optionName, OptionType.COLOR_TYPE, description, help, null, true, editor);
		this.colorId = colorId;
	}

	@Override
	public Color getCurrentValue() {
		GColor gColor = new GColor(colorId);
		if (gColor.isUnresolved()) {
			return null;
		}
		return gColor;
	}

	@Override
	public Object getDefaultValue() {
		return getCurrentValue();
	}

	@Override
	public void doSetCurrentValue(Object value) {
		Gui.setColor(colorId, (Color) value);
	}

	@Override
	public boolean isDefault() {
		return !Gui.isChangedColor(colorId);
	}

	@Override
	public void restoreDefault() {
		Gui.restoreColor(colorId);
	}

}
