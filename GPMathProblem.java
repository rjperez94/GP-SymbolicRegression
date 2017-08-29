import java.util.ArrayList;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.function.*;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

public class GPMathProblem extends GPProblem {
	public static Variable vx;
	protected static Float[] x = new Float[20];
	protected static Float[] y = new Float[20];
	private final GPConfiguration config;

	public GPMathProblem(GPConfiguration config, ArrayList<Double> input, ArrayList<Double> output) throws InvalidConfigurationException {
		this.config = config;
		
		for (int i = 0; i < 20; i++) {
			x[i] = input.get(i).floatValue();
			y[i] = output.get(i).floatValue();
			System.out.println(i + ") " + x[i] + " " + y[i]);
		}
	}

	@Override
	public GPGenotype create() throws InvalidConfigurationException {
		Class[] types = { CommandGene.FloatClass };
		Class[][] argTypes = { {} };

		// Define the commands and terminals the GP is allowed to use.
		// -----------------------------------------------------------
		CommandGene[][] nodeSets = { {
				vx = Variable.create(config, "X", CommandGene.FloatClass),
				new Add(config, CommandGene.FloatClass),
				new Subtract(config, CommandGene.FloatClass),
				new Multiply(config, CommandGene.FloatClass),
				new Divide(config, CommandGene.FloatClass),
				new Pow(config, CommandGene.FloatClass),
				//new Exp(config, CommandGene.FloatClass),
				//new Log(config, CommandGene.FloatClass),
				new Terminal(config, CommandGene.FloatClass, 1.0d, 37.0d, false), } };
		// ----------------------------------------
		return GPGenotype.randomInitialGenotype(config, types, argTypes,
				nodeSets, 100, true);
	}

	public static class FunctionFitnessFormula extends GPFitnessFunction {

		@Override
		protected double evaluate(IGPProgram ind) {
			double error = 0.0f;
			Object[] noargs = new Object[0];
			for (int i = 0; i < 20; i++) {
				vx.set(x[i]);
				try {
					double result = ind.execute_float(0, noargs);
					error += Math.abs(result - y[i]);
					if (Double.isInfinite(error)) {
						return Double.MAX_VALUE;
					}
				} catch (ArithmeticException ex) {
					// This should not happen, some illegal operation was used
					System.out.println("x = " + x[i].floatValue());
					System.out.println(ind);
					throw ex;
				}
			}
			// In case the error is small enough
			if (error < 0.001) {
				error = 0.0d;
			}
			return error;
		}

	}
}
