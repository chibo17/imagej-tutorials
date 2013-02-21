/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */

import ij.ImagePlus;
import imagej.command.Command;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/** A command that preserves the labels from one image to another. */
@Plugin(type = Command.class)
public class CopyLabels extends LegacyCompatibleCommand {

	/** Image with desired labels. */
	@Parameter
	private ImagePlus source;

	/** Image to which labels should be assigned. */
	@Parameter
	private ImagePlus target;

	@Override
	public void run() {
		final int sourceSize = source.getStackSize();
		final int targetSize = target.getStackSize();
		if (sourceSize != targetSize) {
			cancel("Source and target images must have the same number of slices." +
				"(" + sourceSize + " != " + targetSize +")");
			return;
		}
		for (int i=1; i<=sourceSize; i++) {
			final String label = source.getStack().getSliceLabel(i);
			target.getStack().setSliceLabel(label, i);
		}
	}

}
