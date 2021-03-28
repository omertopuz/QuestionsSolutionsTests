package net.questions;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class App {

	public static void main(String[] args) throws Exception {

//		producerConsumerImp();
		callableExample();

		

//		HashMap<String,String> map = new HashMap<>();
//		IntStream.range(0,25).forEach(i->{
//			map.put(i+"-anything",i+"something");
//		});
//
//
//		ArrayList<String> l = new ArrayList<>();
//		l.add("1fdcefc");
//		l.add("2fdcefc");
//		l.add("3fdcefc");
//		String reduce = l.stream().reduce("cdc->", (a, b) -> a.equals(b)?"no":"yes");
//		Boolean reduce2 = l.stream().reduce(Boolean.TRUE, (a, b) -> a.equals(b),(aBoolean, aBoolean2) -> aBoolean && aBoolean2);
//		Integer reduce3 = l.stream().reduce(0, (a, b) -> a. ,(integer, integer2) -> integer * integer2);


//		LinkedList<String> ll = new LinkedList<>();


		/*
		Optional<String> GOT = Optional.of("Game of Thrones");

		Qwert cvb = new Qwert() {
			@Override
			public int hashCode() {
				return super.hashCode();
			}
		};

		cvb.a1 = 5;
		System.out.println("cvb.a1 : "+cvb.a1);

		System.out.println(GOT.filter(s -> s.equals("GAME OF THRONES")).orElse("dddddd"));
		System.out.println(GOT.filter(s -> s.equalsIgnoreCase("GAME OF THRONES")).orElse("eeee"));

		List<Asdf> rty = new LinkedList<>();
		StringJoiner sj = new StringJoiner("],[","[","]");
		sj.add("dgdf").add("gvfdrebgf").add("ewretr");
		System.out.println(sj.toString());

		String imm = "csdfvasdfd";
		System.out.println(imm.hashCode());

		List<Asdf> asdf = Stream.of(new Asdf(1,"fsd"),new Asdf(2,"2-first"),new Asdf(2,"2-second"),new Asdf(3,"kuııyg")).collect(Collectors.toList());


		Asdf f1= new Asdf(2,"2-first");
		Asdf f2= new Asdf(2,"2-second");

		List<Asdf> asdfg = asdf.stream().collect(Collectors.toUnmodifiableList());
		asdfg.get(0).setP1(9);
		System.out.println(asdfg.get(0).getP1());
//		asdfg.remove(f1);
//		asdf.stream().collect(Collectors.maxBy(p->p.getP1()))

		System.out.println("Function.identity() : "+ Function.identity());

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		numbers.stream().reduce(0,(x,y)-> x+y);

		System.out.println("f1.contains / f2.contains ==> "+asdf.contains(f1) + " / " + asdf.contains(f2));

		asdf.remove(f1);

		System.out.println("after f1 removed; f1.contains / f2.contains ==> "+asdf.contains(f1) + " / " + asdf.contains(f2));

		asdf.remove(f1);

		System.out.println("after f1 re-removed; f1.contains / f2.contains ==> "+asdf.contains(f1) + " / " + asdf.contains(f2));

//		asdf.forEach(p->System.out.println(p));

		asdf.set(1,new Asdf(2,"fsd-updated"));

		Iterator<Asdf> iterator = asdf.iterator();
		while (iterator.hasNext()){
			Asdf p = iterator.next();
			if (p.getP1() == 2){
//				asdf.remove(p);
//				asdf.add(1,new Asdf(4,"new element"));
				iterator.remove();
			}
		}
		asdf.set(1,new Asdf(2,"fsd-updated"));
		asdf.removeIf(p->p.getP1() ==1);

		Map<String,String> s = new HashMap<>();


//		asdf.forEach(p->{
//			if (p.getP1() == 2){
//				asdf.remove(p);
//				asdf.add(1,new Asdf(4,"new element"));
//			}
//		});

		List<String> dfg =List.of("dfg-1","dfg-1");

		dfg.forEach(p->System.out.println(p));

		asdf.forEach(p->System.out.println(p));
*/
	}

	private int notImpl() {
		throw new UnsupportedOperationException();
	}

	private static void callableExample() {
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("#cores: " + cores);
		ExecutorService executorService = Executors.newFixedThreadPool(cores);
		List<Future<String>> list = new ArrayList<>();

		IntStream.range(0,24).forEach(i->{
			list.add(executorService.submit(new Processor(i + 1 + "")));
		});

		list.forEach(p-> {
			try {
				System.out.println(p.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		});
		executorService.shutdown();
	}

	public static void producerConsumerImp() throws InterruptedException {
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(2);

		Thread producerThread = new Thread(() -> {
			try {
				int value = 0;
				while (true) {
					blockingQueue.put(value);
					System.out.println("Produced " + (value++));
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread consumerThread = new Thread(() -> {
			try {
				while (true) {
					int value = blockingQueue.take();
					System.out.println("Consume " + value);
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		producerThread.start();
		consumerThread.start();

		producerThread.join();
		consumerThread.join();
	}
}

class Processor implements Callable<String>{
	private String id;

	public Processor(String id){
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "id: " + id;
	}
}

abstract class Qwert{
	protected int a1;
	protected String a2;
}

class Asdf{
	private int p1;
	private String p2;

	public Asdf(int p1, String p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	@Override
	public String toString() {
		return "Asdf{" +
				"p1=" + p1 +
				", p2='" + p2 + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Asdf asdf = (Asdf) o;
		return p1 == asdf.p1 && Objects.equals(p2, asdf.p2);
	}

	@Override
	public int hashCode() {
		return p1;
	}
}
