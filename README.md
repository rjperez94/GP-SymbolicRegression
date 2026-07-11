# GP-SymbolicRegression

## Compiling Java files using Eclipse IDE

1. Download this repository as ZIP
2. Create new `Java Project` in `Eclipse`
3. Right click on your `Java Project` --> `Import`
4. Choose `General` --> `Archive File`
5. Put directory where you downloaded ZIP in `From archive file`
6. Put `ProjectName/src` in `Into folder`
7. Click `Finish`

### Linking JGAP library dependencies

8. Download JGAP from <a href='https://sourceforge.net/projects/jgap/files/jgap/JGAP%203.6.3/jgap_3.6.3_full.zip/download'>here</a>
9. Extract the `jgap.jar` from the `{root}` of the zip
10. Extract the `commons-lang-2.1.jar` and `log4j.jar` from `{root/lib}` of the zip
11. Right click on your `Java Project` --> `Build Path` --> `Add External Archives`
12. Find the `jgap.jar`, `commons-lang-2.1.jar` and `log4j.jar`
13. Click `Open`

### Linking the UI Library

14. Right click on your `Java Project` --> `Build Path` --> `Add External Archives`
15. Select `ecs100.jar` and link it to the project. That JAR will be in the directory where you downloaded ZIP

## Running the program

1. Right click on your `Java Project` --> `Run As` --> `Java Application`
2. Program will ask for directory of where the text files are
3. Choose `data` directory
4. Program will look for `regression.txt` inside that chosen folder

## Build an executable using IntelliJ IDEA

1. Go to **File** → **Project Structure** → **Artifacts**.
2. Click the green plus (**+**) button, select **JAR**, and choose **From modules with dependencies...**
3. In the **Main Class** field, click the folder icon and select the application's entry point class.
4. Under **JAR files from libraries**, select **extract to the target JAR** (this creates the single Fat JAR).
5. Click **OK**, then click **Apply**.
6. From the top menu bar, go to **Build** → **Build Artifacts...** and click **Build**.
7. The executable jar file will be generated inside the project directory under `out/artifacts/`.

### Run the executable JAR file using the command line:

```bash
java -jar path/to/executable.jar
```

## Live Demo

You can run this application directly in your web browser via the link below:

**[Launch Live Demo](https://rjperez94.github.io/GP-SymbolicRegression/)**

### Loading Local Data

If you are trying to pick a file from your physical hard drive, you cannot browse your local folders through the Java window. You must use the bridge upload feature.

1. Look at the very top right of the Java window's title bar for a small **Up Arrow (Upload)** button.
2. Click it to trigger your **native browser file picker** (this one can see your real computer folders).
3. Select your local file. The app will silently drop it into the virtual folder named `/files/uploads/`.
4. Now, inside your Java file picker, type `/files/uploads/` into the file path bar and press **Enter** to find your uploaded file.


## About the Data File

In a 2D (two-dimensional) space (x-y coordinators), there are 20 points (x-y pairs)

## Goal

Find a mathematical function to describe the relationship between the output variable `y` and input variable `x`
