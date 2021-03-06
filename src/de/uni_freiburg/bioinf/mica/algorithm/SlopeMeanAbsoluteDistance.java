/**
 * 
 */
package de.uni_freiburg.bioinf.mica.algorithm;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

/**
 * Computes the mean absolute distance between the first derivatives (slopes) of two curves. 
 * This is done by summing the slope differences
 * between both curves for a uniformly distributed number of integration points
 * and finally normalizing the sum with the number of samples taken.
 * 
 * @author Mmann
 *
 */
public class SlopeMeanAbsoluteDistance extends SampledCurveDistance {
	
	
	/**
	 * Constructs a distance function that uses the given number of interpolation
	 * points.
	 * @param sampleNumber number of interpolation points used for the distance computation (>=2)
	 * @throws OutOfRangeException if sampleNumber is < 2
	 */
	public SlopeMeanAbsoluteDistance(int sampleNumber) throws OutOfRangeException {
		super(sampleNumber);
	}
	
	@Override
	public String getDescription() {
		return "Computes the mean absolute slope difference on "+sampleNumber+" equidistant x-coordinate samples";
	}

	/**
	 * Computes the absolute difference of the slope at the given x-coordinates corrected by the
	 * given length warping information i.e.
	 * 
	 *  abs( (curve1.getSlope(x1)/lengthRatio1) - (curve2.getSlope(x2)/lengthRatio2) )
	 *  
	 */
	@Override
	protected double getDistance(Curve curve1, Curve curve2, double x1, double x2, double lengthRatio1, double lengthRatio2) {
		return FastMath.abs( (curve1.getSlope(x1)/lengthRatio1) - (curve2.getSlope(x2)/lengthRatio2) );
	}
	
	/**
	 * returns the mean distance = sum/samples.
	 */
	@Override
	protected double finalDistance(double distanceSum, int samples) {
		return distanceSum /(double)samples;
	}
	
	/**
	 * returns the distance * samples.
	 */
	@Override
	protected double preFinalDistance(double distanceSum, int samples) {
		return distanceSum * (double)samples;
	}
	
}
