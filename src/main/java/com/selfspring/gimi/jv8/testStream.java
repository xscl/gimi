package com.selfspring.gimi.jv8;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Slf4j
public class testStream {
	static List<Dish> menu = Arrays.asList(
			new Dish("pork",false,800,Dish.Type.MEAT),
			new Dish("beef",false,700,Dish.Type.MEAT),
			new Dish("chichken",false,400,Dish.Type.MEAT),
			new Dish("french fries",true,530,Dish.Type.OTHER),
			new Dish("rice",true,350,Dish.Type.OTHER),
			new Dish("season fruit",true,120,Dish.Type.OTHER),
			new Dish("pizza",true,550,Dish.Type.OTHER),
			new Dish("prawns",false,300,Dish.Type.FISH),
			new Dish("salmon",false,450,Dish.Type.FISH)
			);


	public static void filterStream(){
		System.out.println("size:"+menu.size());
		List<String> collect = menu.stream().filter(Dish::isVegetarian).limit(2).map(Dish::getName).collect(toList());
		System.out.println(JSON.toJSONString(collect));

	}


	public static void flatStream(){
		List<String> words = Arrays.asList("hello,world,my,name,is,gimi".split(","));
		List<String[]> collect = words.stream()
									.map((x) -> x.split(""))
				                    .distinct()
								    .collect(toList());
		List<String> collect1 = words.stream()
									.map((x) -> x.split(""))
								    .flatMap(Arrays::stream)
				                    .distinct()
								    .collect(toList());
		System.out.println(JSON.toJSONString(collect));
		System.out.println(JSON.toJSONString(collect1));

	}

	public static void flatStream1(){
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(4, 5, 6);
		List<int[]> collect = list1.stream().flatMap(
				x -> list2.stream().map(
						y -> new int[]{x, y}
				)
		).collect(toList());
		System.out.println(JSON.toJSONString(collect));

	}

	public static void findAny(){
		Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();
		System.out.println(JSON.toJSONString(any));

		menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(
				x-> System.out.println(x.getName()));
	}

	public static void reduce(){
		List<Integer> list1 = Arrays.asList(7,2,3,4,5,6);

		Optional<Integer> reduce = list1.stream().reduce((a, b) -> {
			int sum = a * b;
			System.out.println(a+"*"+b+"="+sum);
			return sum;
		});
		System.out.println(reduce.orElse(0));

		System.out.println(list1.stream().reduce(0,Integer::sum));
		System.out.println(list1.stream().reduce(Integer::max).orElse(0));
		System.out.println(list1.stream().reduce(Integer::min).orElse(0));
	}

	public static void map_reduce(){
		System.out.println(menu.stream().map(d->1).reduce(0,Integer::sum));
		System.out.println(menu.stream().mapToInt(d->1).count());
		System.out.println(menu.stream().mapToInt(d->1).boxed().count());

	}

	public static void foreach(){
		menu.stream().forEach(x-> {
			System.out.println(x.getName());
		});
	}

	public static void range(){
		IntStream range = IntStream.range(0, 10);
		System.out.println(range.count());
	}
	public static void range1(){
		//过滤出b值
//		IntStream.rangeClosed(0,100)
//				 .filter(y-> Math.sqrt(x*x + y*y)%1==0)
//				 .boxed()
//				 .map(y->new int[]{x,y,(int)(Math.sqrt(x*x + y*y))});
//		Stream<int[]> stream =
		IntStream.rangeClosed(1, 100)
				 .boxed()
				 .flatMap(x ->
						IntStream.rangeClosed(x, 100)
								.filter(y -> Math.sqrt(x * x + y * y) % 1 == 0)
								.boxed()
								.map(y -> new int[]{x, y, (int) (Math.sqrt(x * x + y * y))})
						)
				 .forEach(x->{
					 System.out.println(JSON.toJSONString(x));
				 });

		IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(x ->
						IntStream.rangeClosed(x, 100)
								.boxed()
								.map(y -> new int[]{x, y, (int) (Math.sqrt(x * x + y * y))})
						        .filter(y -> y[2]%1==0)
				)
				.forEach(x->{
					System.out.println(JSON.toJSONString(x));
				});
	}

	public static void createStream() {
		//值创建
		Stream<String> java = Stream.of("java", "python", "net", "c");
		java = Stream.empty();
		//数组创建
		int sum = Arrays.stream(new int[]{1,2,3,4}).sum();
		System.out.println(sum);
		//由文件创建
		long uniqueWord = 0;
		try {
			Stream<String> data = Files.lines(Paths.get("src/main/resources/data.sql"), Charset.defaultCharset());
			uniqueWord = data.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
			System.out.println(uniqueWord);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		//由函数生成:迭代器和生成器
		Stream.iterate(0,n->n+2).limit(10).forEach(System.out::print);
		System.out.println();

		Stream.iterate(new int[]{0,1},n->new int[]{n[1],n[0]+n[1]})
				.map(t->t[0])
				.limit(10)
				.forEach(x-> System.out.print(x + ","));
		System.out.println();

		Stream.generate(Math::random)
				.limit(10)
				.forEach(System.out::print);
		System.out.println();

		IntStream.generate(()->1).limit(10).forEach(System.out::print);
		System.out.println();

		LongStream.generate(new LongSupplier() {
			private long pre = 0;
			private long now = 1;
			@Override
			public long getAsLong() {
				long fresh = pre + now;
				this.pre = this.now;
				this.now = fresh;
				return fresh;
			}
		}).limit(100).forEachOrdered(new LongConsumer() {
			private int index;
			@Override
			public void accept(long value) {
				index++;
				System.out.print(value+",");
				if(index % 10 == 0){
					System.out.println();
				}
			}
		});
	}

	public static void collect_exa() {
		System.out.println(menu.stream().collect(Collectors.counting()));

		System.out.println(menu.stream()
							   .collect(maxBy(Comparator.comparing(Dish::getCalories))));

		IntSummaryStatistics collect = menu.stream()
										   .collect(summarizingInt(Dish::getCalories));
		System.out.println(collect);

		String collect1 = menu.stream()
				.map(Dish::getName).collect(joining(","));
		System.out.println(collect1);

		//广义的规约
		int sum = menu.stream().collect(reducing(0,Dish::getCalories,(i,j)->i+j));
		System.out.println(sum);

		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		List<Integer> numbers = stream.reduce(
				new ArrayList<Integer>(),
				(List<Integer> l, Integer e) -> {
					l.add(e);
					return l; },
				(List<Integer> l1, List<Integer> l2) -> {
					l1.addAll(l2);
					return l1; });
	}


	public enum CaloriLevel{ LOW,MID,HIGH}
	public static void groupBy() {
		Map<Dish.Type, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(collect);

		//groupingBy自定义分类key
		Map<CaloriLevel, List<Dish>> collect1 =
		menu.stream().collect(groupingBy(x -> {
					if (x.getCalories() <= 400) {
						return CaloriLevel.LOW;
					} else if (x.getCalories() <= 700) {
						return CaloriLevel.MID;
					} else {
						return CaloriLevel.HIGH;
					}
				}
		));


		//多级分组
		Map<Dish.Type, Map<CaloriLevel, List<Dish>>> collect2 =
		menu.stream().collect(
				groupingBy(Dish::getType,
						groupingBy(x -> {
							if (x.getCalories() <= 400) {
								return CaloriLevel.LOW;
							} else if (x.getCalories() <= 700) {
								return CaloriLevel.MID;
							} else {
								return CaloriLevel.HIGH;
							}
						})
				)
		);

		//按照子组搜集数据 groupingBy(f)==groupingBy(f,toList())
		Map<Dish.Type, Long> collect3 =
		menu.stream().collect(
				groupingBy(Dish::getType, counting()));

		//收集数据后去掉optional
		Map<Dish.Type, Optional<Dish>> collect4 =
		menu.stream().collect(
				groupingBy(Dish::getType,
					maxBy(Comparator.comparing(Dish::getCalories))
				));
		menu.stream().collect(
				groupingBy(Dish::getType,
					collectingAndThen(
							maxBy(Comparator.comparing(Dish::getCalories)),
							Optional::get)
				));

		Map<Dish.Type, Integer> collect5 =
				menu.stream().collect(
						groupingBy(Dish::getType,
								summingInt(Dish::getCalories)));
		System.out.println(JSON.toJSONString(collect5));

		menu.stream().collect(
				groupingBy(Dish::getType,
				mapping(x -> {
						if (x.getCalories() <= 400) {
							return CaloriLevel.LOW;
						} else if (x.getCalories() <= 700) {
							return CaloriLevel.MID;
						} else {
							return CaloriLevel.HIGH;
						}
					},toSet())
				));
	}

	public static void partitionBy() {
		Map<Boolean, List<Dish>> collect =
		menu.stream().collect(partitioningBy(Dish::isVegetarian));

		//质数分类
		//判断质数的谓词:不能被2~self的任何数整除 == 不能被2~sqrt(self)的任何数整除
		Map<Boolean, List<Integer>> collect1 =
				IntStream.rangeClosed(1, 100).boxed()
						 .collect(partitioningBy(x -> isPrime(x)));
		System.out.println(JSON.toJSONString(collect1));

	}

	public static boolean isPrime(int x) {
		int max = (int) Math.sqrt((double) x);
		return IntStream.rangeClosed(2,max).noneMatch(t -> x % t == 0);
	}

	public static void main(String[] args) {
		partitionBy();
	}





	public static void useSteam() {
		List<String> ThreeHighCaloricDishNames = menu.stream()
										 .filter( d -> d.getCalories() > 300) //Java编译器会从上下文中推断出用什么函数式接口来配合lambda表达式，即可以推断出合适的lambda签名
										 .map( d -> d.getName())
										 .limit(3)
										 .collect(toList());
		//stream()：得到一个流
		//filter,map,limit对流的操作，返回另一个流
		//collect，返回一个结果
		
		//lambda使用的条件是函数式接口，而filter接受一个Predicted函数接口，Predicted函数式接口已经由系统帮我们实现，
		//这里的d.getCalories()是行为的实现过程中，我们使用的一个外部方法，和函数式接口无关；这里的d.getCalories()>300只是行为而已；
		//不可混淆以为Dish需要提供一个函数接口。。。；
		System.out.println(ThreeHighCaloricDishNames);
	}
	//limit, map
	public static void midleOperator() {
		List<String> names =
				menu.stream()
				.filter(d -> {
					System.out.println("filtering " + d.getName());
					return d.getCalories()>300;
				})
				.map(d -> {
					System.out.println("mapping " + d.getName());
					return d.getName();
				})
				.limit(3)
				.collect(toList());
		System.out.println(names);
	}
	//distinct
	public static void distincttest() {
		List<Integer> numbers = Arrays.asList(1,2,1,3,3,1,2,5,8,200);
		numbers.stream()
			   .filter( i -> i % 2 == 0)
			   .distinct()
			   .forEach(System.out::println);

	}
	//映射：map,map方法会接受一个函数作为参数，这个函数会被映射到每个元素上，并将其映射为一个新的元素；
	public static  void toMapping() {
		// TODO Auto-generated method stub
		List<String> dishName = menu.stream()
				.map(Dish::getName)
				.collect(toList());
	}
	public static void toMultipMapping() {
		List<String> words =Arrays.asList("hello","world");
		//使用map，返回的流是一个整体，即Stream<String[]>类型的，里面的元素为  0号元素为 hello，1号元素为world;
		List<String[]> Characters = words.stream()
				.map(word -> word.split(""))
				.distinct()
				.collect(toList());
		
		System.out.println(Characters);
		
		//使用flatMap,将数组映射成流的内容；
		List<String> uniqueCharacters = words.stream()
				.map(word -> word.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(toList());
		
		System.out.println(uniqueCharacters);	
	}
	//anyMatch方法返回一个boolean，检查行为至少匹配一个元素，是终端操作 ；
	//allMatch方法返回一个boolean，检查行为是否全部满足要求，是终端操作；
	//noneMath方法返回一个boolean,检查行为是否没有一个元素u，是终端操作；
	public static void anyMatch() {
		// TODO Auto-generated method stub
		if(menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is vegetarian friendsly");
		}
		if(menu.stream().allMatch(d -> d.getCalories() < 500)) {
			System.out.println("the all menu is lg 1000");
		}
		if(menu.stream().noneMatch(d -> d.getCalories() >=1000)) {
			System.out.println("no  match");
		}
	}
	//寻找第一个元素
	public static void findElement() {
		// TODO Auto-generated method stub
		List<Integer> number = Arrays.asList(1,2,3,4,5);
		Optional<Integer> firstSquareDivisibleByThree =
				number.stream()
				.map(x -> x* x)
				.filter(x -> x % 3 == 0)
				.findFirst();
		System.out.println(firstSquareDivisibleByThree);
	}
	//归约： reduce操作将流流中所有元素反复结合起来，得到一个值；
	public static  void toSum() {
		// TODO Auto-generated method stub
		List<Integer> number = Arrays.asList(1,2,3,4,5);
		int  sum = number.stream().reduce(0, (a,b) -> a + b);
		System.out.println(sum);
	}
	//数值流
	//mapToInt会从没到菜中提取热量，并返回一个IntStream,然后可以调用IntStream接口中定义的sum方法；
	public static void valueStream() {
		int calories = menu.stream()
				.mapToInt(Dish::getCalories)
				.sum();
		
		//将数值流在转换会对象流
		IntStream inStream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> stream = inStream.boxed();
		
		System.out.println(calories);
	}
	//构建流
	public void constructStream() {
		//由值创建流
		Stream<String> stream = Stream.of("java " ,"c++","python","c","vb");
		stream.map(String::toUpperCase).forEach(System.out::println);
		//由数组创建流
		int [] numbers = {1,2,3,9,10};
		int sum = Arrays.stream(numbers).sum();
		//由文件生成流
		long uniqueWords = 0;
		try(Stream<String> lines = Files.lines(Paths.get("src/main/resources/data.sql"), Charset.defaultCharset())){
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
		}catch(IOException e) {
		}
		//无限流
		//iterate方法接受一个初始值，还有一个依次应用在每个产生的新值上的Lambda，
		Stream.iterate(0, n -> n+2)
		      .limit(10)
		      .forEach(System.out::println);
		//generate方法，接受一个Supplier<T> 类型的Lambda提供的新值；
		Stream.generate(Math::random)
		      .limit(10)
		      .forEach(System.out::println);
	}
	public enum CaloricLevel {DIET,NORMAL,FAT};
	//分组
	public static void toGroupingBy() {
		//一级分类
		Map<CaloricLevel,List<Dish>> dishesByCaloricLevel = menu.stream().collect(
				groupingBy(dish->{
					if(dish.getCalories() <= 400) return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
					else return CaloricLevel.FAT;
				}));
		System.out.println(dishesByCaloricLevel);
		//多级分类
		Map<Dish.Type,Map<CaloricLevel,List<Dish>>> dishesByTypeCaloricLevel = 
				menu.stream().collect(
					groupingBy(Dish::getType,
							groupingBy(dish -> {
								if(dish.getCalories() <= 400) return CaloricLevel.DIET;
								else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
								else return CaloricLevel.FAT;
							})));
		System.out.println(dishesByTypeCaloricLevel);
	}
	//分类
	public static void classcify() {
		//分区：是分组的特殊情况，有一个谓词最为分类函数，分区函数返回一个布尔值，意味着得到的分组Map的键类型是boolean；最多可以分为两组
		Map<Boolean,List<Dish>> partitionedMenu = 
				menu.stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println(partitionedMenu);
	}

	public static void testPaths() {
		//以当前路径作为Path对象
		Path p = Paths.get("src/main/resources/data.sql");
		//使用传入的字符串返回一个Path对象
		Path p2 = Paths.get("D","ReviewIO","URL");
		//对应的路径
		System.out.println("p对象的对应路径：" + p.toString());
		System.out.println("p2对象的对应路径：" + p2.toString());
		//路径数量是以路径名的数量作为标准
		System.out.println("p路径数量：" + p.getNameCount());
		System.out.println("p2路径数量：" + p2.getNameCount());
		//获取绝对路径
		System.out.println("p绝对路径：" + p.toAbsolutePath());
		System.out.println("p2绝对路径：" + p2.toAbsolutePath());
		//获取父路径
		System.out.println("p父路径："  + p.getParent());
		System.out.println("p2父路径：" + p2.getParent());
		//获取p2对象的文件名或者文件目录名
		System.out.println(p2.getFileName());
		//通过Path对象返回一个分隔符对象
		Spliterator<Path> split = p2.spliterator();
	}
	
}