package model;

import java.util.ArrayList;

public class ProcedureOD {

	private static ProcedureOD instance = null;

	private int[][] priorityMatrix = new int[101][101];

	public static ProcedureOD getProcedureODInstance() {
		if (instance == null)
			instance = new ProcedureOD();
		return instance;
	}

	private ProcedureOD() {
		for (int i = 0; i < priorityMatrix.length; i++) {
			priorityMatrix[i][i] = 0;
		}
	}

	public void procedureODSetup(ArrayList<Entry> entryList) {
		entryList.forEach(a -> addPriority(a));
	}

	public void addPriority(Entry entry) {
		int c = entry.getA();
		int p = entry.getB();
		priorityMatrix[c][p] = 1;
	}

	public int getPriority(int a, int b) {
		return priorityMatrix[a][b];
	}

	public int matrixSize() {
		return priorityMatrix.length;
	}
}
