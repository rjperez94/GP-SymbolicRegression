import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

public class SymbolicRegression {
	private static JFileChooser fileChooser = new JFileChooser();
	private static final String TRAINSET_FILENAME = "regression.txt";
	private static final int MAX_GENERATIONS = 5000;
	private static ArrayList<Double> x = new ArrayList<Double>();
	private static ArrayList<Double> y = new ArrayList<Double>();

	public static void main(String[] args) throws InvalidConfigurationException {
		chooseDir();

		GPConfiguration config = new GPConfiguration();
		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		//config.setMinInitDepth(6);
		config.setMaxInitDepth(6);
		config.setPopulationSize(100);
		config.setFitnessFunction(new GPMathProblem.FunctionFitnessFormula());
		//config.setReproductionProb(75f);
		//config.setMutationProb(1f);
		config.setCrossoverProb(90f);
		GPMathProblem problem = new GPMathProblem(config, x, y);
		GPGenotype gp = problem.create();

		gp.setVerboseOutput(true);

		System.out.println("Maximum of " + MAX_GENERATIONS + " generations");
		for (int i = 0; i < MAX_GENERATIONS; i++) {
			gp.evolve(1);
			if (gp.getAllTimeBest() != null && gp.getAllTimeBest().getFitnessValue() == 0) {
				System.out.println("\nFound a program with fitness of 0.0 after " + i + " generations\n");
				break;
			}
		}
		gp.outputSolution(gp.getAllTimeBest());
	}

	private static void chooseDir() {
		File train = null;

		// set up the file chooser
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Select input directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// run the file chooser and check the user didn't hit cancel
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// get the files in the selected directory and match them to
			// the files we need.
			File directory = fileChooser.getSelectedFile();
			File[] files = directory.listFiles();

			for (File f : files) {
				if (f.getName().equals(TRAINSET_FILENAME)) {
					train = f;
				}
			}

			// check none of the files are missing, and call the load
			// method in your code.
			if (train == null) {
				JOptionPane.showMessageDialog(null, "Directory does not contain correct files", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} else {
				readFile(train);
			}
		}
	}

	private static void readFile(File train) {
		
		try {
			Scanner scan = new Scanner(train);
			// Skip through the headers
			while (!scan.hasNextDouble()) {
				scan.nextLine();
			}

			while (scan.hasNextLine() && scan.hasNextDouble()) {
				x.add(scan.nextDouble());
				y.add(scan.nextDouble());
			}

			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
