package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Db.SchedulerDb;
import model.Employee;
import model.Procedure;
import model.ProcedureEntry;
import model.Product;
import model.SaleOrder;
import model.SaleOrderEntry;

public class SchedulerController {
	SchedulerDb schedulerDb = new SchedulerDb();
	ProcedureODController procedureODController = new ProcedureODController();
	EmployeeController employeeController = new EmployeeController();
	SaleOrderController saleOrderController = new SaleOrderController();
	ProcedureController procedureController = new ProcedureController();
	ArrayList<Employee> employeeList = new ArrayList<>();
	ArrayList<SaleOrderEntry> orderProcedureList = new ArrayList<>();
	int lowestTime;
	SaleOrder mostUrgentOrder;
	HashMap<Integer, Integer> scheduleMap = new HashMap<>();
	ArrayList<SaleOrder> orders = new ArrayList<>();
	String employeeTypeRequired = null;
	boolean done = false;
	private static SchedulerController instance;

	public static SchedulerController getSchedulerControllerInstance() throws DataAccessException, SQLException {
		if (instance == null) {
			instance = new SchedulerController();
		}
		return instance;
	}

	private SchedulerController() throws DataAccessException, SQLException {
		schedulerDb.getEmployeeAndProcedure().forEach((k, v) -> {
			if (v != null) {
				try {
					procedureController.updateNotDone(v.getCode());
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
		employeeList.addAll(employeeController.getAllEmployees());
		HashMap<Integer, Integer> tempMap = new HashMap<>();
		employeeList.forEach(e -> {
			tempMap.put(e.getId(), -1);
		});
		schedulerDb.reset();
		schedulerDb.insertEmployeeAndProcedure(tempMap);
	}

	public void removeDone(Employee e) throws SQLException, DataAccessException {
		scheduleMap.replace(e.getId(), -1);
		schedulerDb.reset();
		schedulerDb.insertEmployeeAndProcedure(scheduleMap);
	}

	public void schedule() throws DataAccessException {
		scheduleMap.clear();
		orderProcedureList.clear();
		orders.clear();
		schedulerDb.getEmployeeAndProcedure().forEach((k, v) -> {
			if (v != null && k != null) {
				scheduleMap.put(k.getId(), v.getCode());
			} else {
				scheduleMap.put(k.getId(), -1);
			}
		});
		orderProcedureList.addAll(getSaleOrderData());
		orderProcedureList.forEach(g -> orders.add(g.getSaleOrder()));
		// ----------------------------------------------
		orders.forEach(e -> {
			System.out.println(e);
		});
		System.out.println();
		// ----------------------------------------------
		System.out.println();
		orders = orderOrders(orders);
		// ----------------------------------------------
		orders.forEach(e -> {
			System.out.println(e);
		});
		System.out.println();
		// ----------------------------------------------
		procedureODController.ProcedureODSetup();
		orderProductsProcedures(orderProcedureList);
		// ----------------------------------------------
//		scheduleMap.forEach((k, v) -> {
//			System.out.println(k + " " + v);
//		});
		System.out.println();
		// ----------------------------------------------
		scheduleMap.forEach((k, v) -> {
			if (v == -1) {
				employeeList.forEach(e -> {
					if (e.getId() == k) {
						basicAssign(e.getPosition().getPositionName());
						// ----------------------------------------------
						System.out.println("here -------> " + employeeTypeRequired);
						// ----------------------------------------------
					}
				});
				// ----------------------------------------------
				System.out.println("Where am I? " + employeeTypeRequired);
				// ----------------------------------------------
				assign(k, orderProcedureList, orders, employeeTypeRequired);
			}
		});
		// ----------------------------------------------
		scheduleMap.forEach((k, v) -> {
			System.out.println(k + " " + v);
		});
		// ----------------------------------------------
		try {
			schedulerDb.reset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		schedulerDb.insertEmployeeAndProcedure(scheduleMap);
	}

	public HashMap<Employee, Procedure> getSchedule() {

		HashMap<Employee, Procedure> tempMap = new HashMap<>();
		scheduleMap.forEach((k, v) -> {
			employeeList.forEach(e -> {
				if (e.getId() == k) {
					try {
						tempMap.put(e, procedureController.findProcedureByCode(v));
					} catch (DataAccessException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
				}
			});
		});
		return tempMap;
	}

	private void basicAssign(String b) {
		employeeTypeRequired = b;
	}

	private void orderProductsProcedures(ArrayList<SaleOrderEntry> orderProcedureList) {
		ArrayList<Integer> tempList = new ArrayList<>();
		ArrayList<ProcedureEntry> tempProdedureEntryList = new ArrayList<>();
		ArrayList<Integer> procedureList = new ArrayList<>();
		orderProcedureList.forEach(o -> o.getProductAndItsProcedures().forEach((k, v) -> {
			v.forEach(pe -> procedureList.add(pe.getProcedure().getCode()));
			// --------------------------------------------------------------------
			System.out.println("++++++++++++++++++++++++++++");
			procedureList.forEach(e -> {
				System.out.println(e);
			});
			System.out.println();
			// --------------------------------------------------------------------
			tempList.addAll(procedureODController.orderPriority(procedureList));
			// --------------------------------------------------------------------
			tempList.forEach(d -> {
				System.out.println(d);
			});
			System.out.println("++++++++++++++++++++++++++++");
			// --------------------------------------------------------------------
			Iterator<Integer> iterate = tempList.iterator();
			while (iterate.hasNext()) {
				int n = iterate.next();
				v.forEach(pes -> {
					if (pes.getProcedure().getCode() == n) {
						tempProdedureEntryList.add(pes);
					}
				});
			}
			v.clear();
			v.addAll(tempProdedureEntryList);
			procedureList.clear();
			tempList.clear();
		}));
	}

	private void assign(Integer ky, ArrayList<SaleOrderEntry> orderProcedureList, ArrayList<SaleOrder> orders,
			String employeeType) {
		done = false;
		Iterator<SaleOrder> iterateOrders = orders.iterator();
		while (iterateOrders.hasNext() && !done) {
			SaleOrder so = iterateOrders.next();
			int i = 0;
			while (so != orderProcedureList.get(i).getSaleOrder()) {
				i++;
			}
			SaleOrderEntry saleOrderEntry = orderProcedureList.get(i);
			saleOrderEntry.getProductAndItsProcedures().forEach((k, v) -> {
				if (!done && v.size() != 0) {
					System.out.println("I am not here procedures: Listsize " + v.size());
					System.out.println("I was here procedure code: " + v.get(0).getProcedure().getCode());
					System.out.println();
					System.out.println("et req: " + v.get(0).getProcedure().getEmployeeTypeRequired() + " et got: "
							+ employeeType);
					// --------------------------------------------------------------------
					if (v.get(0).getProcedure().getEmployeeTypeRequired().compareToIgnoreCase(employeeType) == 0) {
						// --------------------------------------------------------------------
						System.out.println("I am here");
						// --------------------------------------------------------------------
						scheduleMap.put(ky, v.get(0).getProcedure().getCode());
						done = true;
						try {
							procedureController.updateInWork(v.get(0).getProcedure().getCode());
							v.remove(v.get(0));

						} catch (DataAccessException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}

	private ArrayList<SaleOrder> orderOrders(ArrayList<SaleOrder> orders) {
		ArrayList<SaleOrder> tempOrders = new ArrayList<>();
		HashMap<SaleOrder, Integer> tempOrder = new HashMap<>();
		Iterator<SaleOrder> iterator = orders.iterator();
		while (iterator.hasNext()) {
			SaleOrder next = iterator.next();
			int timeLeft = dateRemaining(next.getDueDate());
			tempOrder.put(next, timeLeft);
		}
		while (!tempOrder.isEmpty()) {
			mostUrgentOrder = null;
			lowestTime = 9999;
			tempOrder.forEach((k, v) -> {
				if (lowestTime > v) {
					setLowestTimeAndMostUrgentOrder(v, k);
				}
			});
			tempOrders.add(mostUrgentOrder);
			tempOrder.remove(mostUrgentOrder);
		}
		return tempOrders;
	}

	private void setLowestTimeAndMostUrgentOrder(int v, SaleOrder k) {
		mostUrgentOrder = k;
		lowestTime = v;
	}

	private int dateRemaining(Date dueDate) {
		Date now = new Date();
		int diff = dueDate.compareTo(now);
		return diff;
	}

	public List<SaleOrderEntry> getSaleOrderData() throws DataAccessException {
		List<SaleOrderEntry> listOfSaleOrderEntries = new ArrayList<SaleOrderEntry>();
		List<SaleOrder> listOfSaleOrder = saleOrderController.getAllSaleOrders();

		for (SaleOrder so : listOfSaleOrder) {
			HashMap<Product, List<ProcedureEntry>> hash = new HashMap<Product, List<ProcedureEntry>>();
			for (Product p : so.getProducts()) {
				List<ProcedureEntry> list = procedureController.getAllProductAndProcedureToIt(p.getProductName());
				hash.put(p, list);
			}
			SaleOrderEntry saleOrderEntry = new SaleOrderEntry(so);
			saleOrderEntry.setProductAndItsProcedures(hash);
			listOfSaleOrderEntries.add(saleOrderEntry);
		}
		return listOfSaleOrderEntries;
	}

}
