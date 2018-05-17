[Data Analysis System](https://hackmd.io/zL2vJsayQLieF1HkQh5mhg?view)
===

## Preface
身為一個`專業的不誤正業工程師`，我拿細菌做過基因工程、代謝工程、醱酵工程；也曾穿梭於化學工廠中當作製程工程師，玩著破億的設備；也曾經搞過高性能高分子複合材料的開發。

在這些不同的領域中穿梭，我始終都會遇到一位又愛又恨的好朋友，它名為 **Data**。
窩在實驗室做研究要分析數據，在大工廠中跑來跑去也要分析數據，回到一般生活中也是要分析數據滴。~~菜籃族看盤囉~~:smirk:

於是乎，我開始幻想著我能不能寫出一套小系統，可以幫我自動地收集數據、整理數據、分析數據，再自動畫圖表呢？~~我就是懶~~:laughing:

幻想許久，終於行動！
以下是非資工、資管本科系的敝人在下我，第一次寫出來的小系統，請各位客官鞭小力點～

## Introduction
傳統製造業往往都是靠著人工在機器面前，觀察參數的變化後，依照現場領班或工程師的經驗進行製程參數調教；但是如此 ***Experience-Driven*** 的製程往往受制於人工的經驗與專注度，因此許多公司便制定許許多多的 SOP，希望人工在某些檢查點監控製程參數；抑或是由人工於固定時間的去抄參數下來，後面再輸入至 Excel 等軟體進行統計分析，或是 ERP 等軟體來產生報表。

>但是，執行 SOP 的依舊是人類，且這樣的做法往往不夠即時···

於是乎，我想寫出一個小系統 (Web Application) 可以讓工程師能夠在螢幕前看到設備的即時資訊、歷史資訊及統計分析的資料，進而做出判斷；如此才能夠從 ***Experience-Driven*** 的控制方法轉換成 ***Data-Driven***，如下圖。

<div style='text-align: center'>
    <img src='https://i.imgur.com/muaclgw.png?2' width='90%'/>
</div>
<br>

1. 首先，將生產線上的機器所產生的數據定時地傳送至資料庫。
2. 工程師可以在瀏覽器上觀察即時資料，且系統可以依照條件針對超出標準的數據發出警告，如 C<sub>pk</sub> < 1.0 時；工程師也可以依照時間區間調閱歷史數據，並且自動產生統計圖表。
3. 如此一來，工程師就能有更充裕的時間去解讀這些統計數據，進而去對整個產線去做出優化改善。

<hr>

整個系統我使用 [Java EE](http://www.oracle.com/technetwork/java/javaee/overview/index.html) 平台來建構，其中資料的提取、分析及傳送的部份我使用 [MVC/Model 2](https://openhome.cc/Gossip/ServletJSP/Model2.html) 的設計方式來規劃，最後以視覺化的方式將統計分析結果呈現在瀏覽器上面。

在 [MVC/Model 2](https://openhome.cc/Gossip/ServletJSP/Model2.html) 架構中，系統分為 Model、Controller 及 View，各別功能如下：

- Controller 
	- 接受請求並驗證
	- 依照使用者所要求的參數不同，判斷要轉發給哪個 Model 或是 View
- Model
	- 執行系統的商業邏輯
- View
	- 提取 Model 的狀態
	- 將商業邏輯的結果呈現於畫面

[MVC/Model 2](https://openhome.cc/Gossip/ServletJSP/Model2.html) 的重點在於經由適當地劃分責任區域，使 Controller 與 Model 不用處理 HTML，而 View 的部分也不會被 Java 程式碼所干擾。如此一來，在更複雜的系統中，Model、Controller 及 View 仍會各司其職，將提高程式的可讀性，使得系統更易於維護。

## Implementation
實作的部分如下：
1. Data Generator
2. Model
	- Mean
	- Standard Deviation
	- Probability Density
	- Process Capability
	- Java Mail
4. View
	- Historic Data
	- Real-Time Data
<hr>

### Data Generator
由於我沒辦法取得製造業的真實機器數據，所以利用程式亂數產生的方式來模擬。
1. 首先，機器常見的數據如時間、溫度、壓力、流量等，針對這些機器的屬性去創建這些物件(Object)。而溫度、壓力等參數通常會有`設定值`及`變化範圍`。例如，你將烘箱設定成 100<sup>。</sup>C，而實際運作時，烘箱溫度卻可能是在 105<sup>。</sup>C~95<sup>。</sup>C 之間遊走。
	於是乎，我就將這些物件設計成一個`設定值`與一個`變化範圍`，例如 100<sup>。</sup>C 與 5%，接著就由亂數產生 105<sup>。</sup>C~95<sup>。</sup>C 的數據。另外，因為小數點的位數在統計上有著`有效位數`和`精確度`的問題，所以接著再用 [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) 統一小數點位數。
2. 接著我再創建一個物件 **Information**，先讓這個物件繼承 [TimerTask](https://docs.oracle.com/javase/7/docs/api/java/util/TimerTask.html)，然後將剛剛的那些物件放進來，之後利用 [JDBC](http://www.oracle.com/technetwork/java/overview-141217.html) 連接 [MySQL](https://www.mysql.com/)，並且將數據寫入資料庫中。
3. 最後，我再用一個 **Machine** 物件，將剛剛的 **Information** 包起來，並且繼承 [Thread](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html)。

看起來好複雜:sweat:，請搭配下面的圖服用～

<div style='text-align: center'>
    <img src='https://i.imgur.com/fvst9Xo.gif' width='90%'/>
</div>
<br>

我當初之所以會搞得這麼複雜，是因為我想要以 [Thread](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html) 呈現每一台機器是各自獨立，其產生各自的數據並傳送至資料庫中(如下圖)，而在 [Java 中一個子類別(Class)只能繼承一個父類別(Class)](https://openhome.cc/Gossip/Java/Polymorphism-is-a.html)。

<div style='text-align: center'>
    <img src='https://i.imgur.com/iBiFtc0.gif' width='90%'/>
</div>
<br>

為了方便起見，我使用 [JFrame](https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html) 與 [ActionListener](https://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionListener.html) 將上述的 **Data Generator** 實作成桌面應用程式。

<div style='text-align: center'>
    <img src='https://i.imgur.com/R6u6OVM.png' width='90%'/>
</div>
<hr>

### Model - Mean
平均值這件事，當然就是 $\bar{x} = \frac{1}{n}\sum\limits_{i=1}^{n}x_i$ 就可以解決啦，但是 Java 的生態系龐大，開源的 package 眾多，真的沒有人造過這顆輪子嗎？

>「不要重新打造輪子」雖然很實在，但是享受打造輪子的過程卻是很爽快

在 Java 的世界裡 [APACHE](https://www.apache.org/) 基金會有著舉足輕重的地位，於是我在 [APACHE](https://www.apache.org/) 輪子館中找到了 [Commons Math: The Apache Commons Mathematics Library](http://commons.apache.org/proper/commons-math/) 這個集合眾多數學公式的 Library，其中的 [Mean](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/descriptive/moment/Mean.html) 便可以方便的計算陣列中的平均值。

```java
public static void main(String[] args) {
	double[] values = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
	Mean mean = new Mean();
	double rst_mean = mean.evaluate(values);
	System.out.println(rst_mean); // 5.5
}
```
<hr>

### Model - Standard Deviation
標準差，在敘述統計上被用來檢視一組資料的離散程度。標準差越低時，表示資料的離散程度低，資料點距離平均值(Mean)越近；反之，當標準差越高時，表示資料的離散程度高，資料點距離平均值(Mean)越遠。標準差又分兩種，**母體標準差**及**樣本標準差**。

#### 母體標準差(Population Standard Deviation):
當整個群體中有 $n$ 個樣本，而且每個樣本的值為 $x_1, x_2,...,x_n$ ，再而且群體中的每一筆資料皆可取得時，就適用於母體標準差。例如：假設向日葵小班有 10 名小朋友，其中每位小朋友的年齡分別是 {3, 5, 4, 5, 3, 3, 5, 4, 5, 3}，帶入以下的公式即可得年齡標準差($\sigma = 0.8944271909999159$)。

$$
\sigma = \frac{1}{n}\sqrt{ \sum\limits_{i=1}^{n}(x_i - \mu)^2 }
$$

Where:
- $\sigma$ is the standard deviation
- $\mu$ is the mean (also called the expected value)


#### 樣本標準差(Sample Standard Deviation):
若我們關心的群體很大，通常是無法取得群體中的每一筆資料，而且為了減少調查成本與增加效率，常會使用抽樣(Sampling)的方式取得樣本，希望以樣本資料來代表整個群體。但是畢竟母體資料與樣本資料仍是有差異的，因此利用樣本標準差來代表母體標準差的**估計值**。
例如：希望取得全國大學生的平均身高與其標準差時，是很難實際量測每一個人的身高，因此可以藉由抽樣的方式，在整個群體中抽取出 $n$ 個樣本，並帶入以下公式。

$$
s = \frac{1}{n-1}\sqrt{ \sum\limits_{i=1}^{n}(x_i - \bar{x})^2 }
$$

Where:
- $s$ is the standard deviation
- $\bar{x}$ is the mean (also called the expected value)

實作的部分，我還是在輪子館中找到了 [Standard Deviation](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/descriptive/moment/StandardDeviation.html):grin:，而且這個類別(Class)還可以藉由設定 `isBiasCorrected` 這個布林值(boolean)來選擇使用**母體標準差**或是**樣本標準差**；若為 `true` 是**樣本標準差**，若為 `false` 則為**母體標準差**。
若需使用母體標準差，可於[建構](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/descriptive/moment/StandardDeviation.html#StandardDeviation(boolean))時傳入 `false` 即可；抑或可先利用[無傳參數建構子](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/descriptive/moment/StandardDeviation.html#StandardDeviation())建構該物件，再利用 [`setBiasCorrected`](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/descriptive/moment/StandardDeviation.html#setBiasCorrected(boolean)) 方法設定為 `false`。

```java
public static void main(String[] args) {
	double[] values = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
	StandardDeviation sd_sample = new StandardDeviation();
	StandardDeviation sd_population = new StandardDeviation(false); // set the isBiasCorrected property to false
	double rst_sd_sample = sd_sample.evaluate(values); // Sample Standard Deviation
	double rst_sd_population = sd_population.evaluate(values); // Population Standard Deviation
	System.out.println(rst_sd_sample); // 3.0276503540974917
	System.out.println(rst_sd_population); // 2.8722813232690143
}
```

<hr>

### Model - Probability Density
機率密度函數(Probability Density Function)簡單來說是指一個值所出現的次數或頻率，最常見的便是**常態分布**(Normal Distribution)又稱為**高斯分布**(Gaussian Distribution)。

若一個隨機變數 $x$ 符合一個平均值為 $\mu$、標準差為 $\sigma$ 的常態分布時，其機率密度函數如下：

$$
f(x|\mu, \sigma^2) = \frac{1}{\sqrt{2\pi\sigma^2}}e^{-\frac{(x-\mu)^2}{2\sigma^2}}
$$

Where:
- $\mu$ is the mean or expectation of the distribution
- $\sigma$ is the standard deviation
- $\sigma^2$ is the variance

而常見的**標準常態分布**則是平均值為 $\mu=0$、標準差為 $\sigma=1$的常態分布，其函數如下：

$$
f(x) = \frac{1}{\sqrt{2\pi}}e^{-\frac{x^2}{2}}
$$

下圖中的紅色曲線即為標準常態分布。當曲線越集中時(藍)，表示其標準差越小、精密度越高；反之，曲線越扁平時(橘)，其標準差越高；若是平均值較預期的有所偏移時，便會像綠色曲線一樣。

<div style='text-align: center'>
    <img src='https://i.imgur.com/WFjDytl.png' width='90%'/>
</div>
<div style='text-align: right'>
    <a href='https://en.wikipedia.org/wiki/Normal_distribution'>Ref.</a>
</div>

常態分布圖再搭配製程中的規格上限值(Upper Spec Limit, USL)與規格下限值(Lower Spec Limit, LSL)時，便可以探討製程的準確度(Accuracy)、精密度(Precision)與良率。

<div style='text-align: center'>
    <img src='https://i.imgur.com/G9CxfGE.png' width='90%'/>
</div>
<div style='text-align: right'>
    <a href='https://calibrationawareness.com/calibration-awareness-what-is-calibration'>Ref.</a>
</div>

承上圖，由右至左來說明：
1. 右一，數據的分布介於 LSL 與 USL 中間，平均值落於規格中心且曲線分布窄(標準差小)。如此的結果表示該製程的產品良率高、品質好且穩定。
2. 右二，數據大致分布於 LSL 與 USL 中間，但有些數據點是超出規格限制；雖然平均值落於規格中心，但是曲線分布寬，表示其標準差大；此製程生產的產品良率雖不低，但是品質不穩定。就像拿一把散彈槍朝靶紙上打，雖然會打中紅心，但是整張靶紙也面目全非，而且還會有少部分的子彈超出靶紙。
3. 左二，雖然曲線與右一相同，但是其平均值較規格中心右偏。此製程的良率高，品質穩定，但若一個不小心更往右偏時，將會生產出很大量的不良品。
4. 左一，這樣的曲線表示這個製程的良率、品質是最差的，不但產品有一大部分是超出規格上限，而且品質落差大；這個就像拿散彈槍打靶，不但自己的靶打不準，還打到隔壁的靶。:joy:
<hr>

介紹完一堆公式後還是必須將這些數學實作出來，於是我依舊在輪子館中找到 [Probability Density](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/distribution/NormalDistribution.html)。以下利用**標準常態分布**作為例子，首先於[建構](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/distribution/NormalDistribution.html#NormalDistribution(double,%20double))時傳入 [Mean](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/descriptive/moment/Mean.html) 與 [Standard Deviation](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/stat/descriptive/moment/StandardDeviation.html) 即可建立 `Class`，接著利用該 `Class` 的 方法 [`density`](http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/distribution/NormalDistribution.html#density(double))，傳入 $x$ 值得相對應的 $y$ 值。

```java
double mean = 0.0;
double sd = 1.0;
double[] x = {-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0};	
double y = 0.0;

NormalDistribution nd = new NormalDistribution(mean, sd);
for (int i = 0; i < x.length; i++ ) {
	y = nd.density(x[i]);
	System.out.println("x : " + x[i] + ", y = " + y);
}
```
```
x : -3.0, y = 0.004431848411938009
x : -2.0, y = 0.053990966513188056
x : -1.0, y = 0.24197072451914337
x :  0.0, y = 0.3989422804014327
x :  1.0, y = 0.24197072451914337
x :  2.0, y = 0.053990966513188056
x :  3.0, y = 0.004431848411938009
```
<hr>

### Model - Process Capability
製程能力(Process Capability)是指製程的各種條件在標準化後，且在統計的管制狀態下所呈現之質與量的控制能力。製程能力又分為製程準確度 $C_a$(Capability of Accuracy)、製程精密度 $C_p$(Capability of Precision)及製程能力指標 $C_{pk}$(Process Capability Index)。

#### 製程準確度(Capability of Accuracy, $C_a$)：
<div style='text-align: center'>
    <img src='https://i.imgur.com/jlb847J.png' width='30%'/>
</div>

$C_a$就像打靶時打中紅心的準度，當子彈越接近紅心(規格中心)時，準確度越高($C_a$越低)。應用於製程時，$C_a$可以代表製程中之實際量測平均值是否接近規格的中心，越接近規格中心，可視為製程準確度高；但是$C_a$並沒有考慮到製程本身的精密程度。

$$
C_a = \frac{\bar{X}-\mu}{(USL - LSL)/2}
$$
$$
\mu = \frac{USL + LSL}{2}
$$

Where:
- $\mu$ is the middle of the sppecification
- $\bar{X}$ is the mean of the measured values in the process
- $USL$ is the Upper Specification Limit
- $LSL$ is the Lower Specification Limit

#### 製程精密度(Capability of Precision, $C_p$)：
<div style='text-align: center'>
    <img src='https://i.imgur.com/LETB0wt.png' width='30%'/>
</div>

$C_p$就像打靶時，子彈集中於相同位置的程度。應用於製程時，$C_p$代表產品之量測值的範圍大小，當量測值的範圍小，可視為此製程的精密度低；但是$C_p$並沒有考慮到製程本身的準確程度。

$$
C_p = \frac{USL - LSL}{6\sigma}
$$

Where:
- $\sigma$ is the standard deviation
- $USL$ is the Upper Specification Limit
- $LSL$ is the Lower Specification Limit

#### 製程能力指標(Process Capability Index, $C_{pk}$)
承上我們可以知道，若是單獨以$C_a$或$C_p$來判斷一個製程的優劣時，會容易有誤判的情形。例如，單兵打靶時，六顆子彈都打到同一個位置($C_p$高，精密度高)，但是他都打到隔壁單兵的靶($C_a$大，精準度低)。如此一來，該說這位單兵的槍法厲害還是不厲害呢？:confused:

因此$C_{pk}$就是綜合考量了$C_a$與$C_p$的製程能力指標。

$$
C_{pk} = C_p * (1 - C_a)
$$
<hr>

實作的部分，我依照製程的$C_a$與$C_{pk}$給予給予**紅綠燈**來表示目前該製程的優劣，例如：
- 當 $C_{pk} > 1.33$ `&&` $C_a < 12.5%$ 時給予**綠燈**，表示製程有足夠的能力
- 當 $1.00 < C_{pk} < 1.33$ 時給予**黃燈**，表示製程能力尚可接受，但需提出改善計畫
- 當 $C_{pk} < 1.00$ `||` $C_a > 25.0%$ 時給予**紅燈**，表示此製程需要立即改善

<div style='text-align: center'>
    <img src='https://i.imgur.com/AYOldKo.png' width='90%'/>
</div>

若製程的品質開始 `Going South`， 燈號就會從**綠燈**變成**黃燈**，**黃燈**主要是一個過渡期，讓工程師可以調整製程參數，想辦法回到**綠燈**，不要讓燈號變成**紅燈**；因為變成**紅燈**的時候，系統就會利用 [JavaMail](https://docs.oracle.com/javaee/7/api/javax/mail/package-summary.html) 自動發送一封 **Alarm 信**到主管的信箱。:scream:

<div style='text-align: center'>
    <img src='https://i.imgur.com/ANUYJj9.png' width='90%'/>
</div>
<hr>

### View - Historic Data
歷史資料的部分，可以依照使用者選擇的時間區間去撈取資料，畫面左邊呈現資料區間中數值的變化，而且當滑鼠移動過去時，可以顯示每個時間點的**數值**；畫面右邊則是將所選取的資料繪製成常態分佈曲線(紅)，黃色線條由左至右則是 **LSL**、**規格中心**、**USL**。

<div style='text-align: center'>
    <img src='https://i.imgur.com/MV12Xx9.png' width='90%'/>
</div>

另外，**`SAVE`** 按鈕可將所選取之歷史資料中的數值使用 [Serializable](https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html)、[I](https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html)/[O](https://docs.oracle.com/javase/7/docs/api/java/io/OutputStream.html) 儲存成常見的 csv 檔。

<div style='text-align: center'>
    <img src='https://i.imgur.com/JafiSdZ.png' width='90%'/>
</div>
<hr>

### View - Real-Time Data
即時資料的部分是呈現最近 10 分鐘的資料，而且利用 [AJAX](https://www.w3schools.com/js/js_ajax_intro.asp) 的方式，每 5 秒更新一次圖表。
<div style='text-align: center'>
    <img src='https://i.imgur.com/nLOISIT.gif' width='90%'/>
</div>


## Demo
<div style='text-align: center'>
    <iframe width="560" height="315" src="https://www.youtube.com/embed/AqZeFpMKLD8" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
</div>

## Conclusion
這個專案非常陽春，功能也不多，但是我以一個非資訊非統計背景的工程師出發，在實作專案的過程中，有用到的 `API` 我都去研究它的 `Sorce code`，盡力地瞭解這些 `API` 的設計原理；統計的部分，我也是將統計的理論知識搞懂後，才會加入專案內。未來希望加入 **Data Science** 的部分，再弄出可以預測未來的系統。:stuck_out_tongue_winking_eye:

> ***不懂的事物不要裝懂*** ，期許自己不要淪為只會使用 `API` 的工程師。

## Reference
- [良葛格學習筆記](https://openhome.cc/Gossip/)
- 林信良 (2014)。 Java SE 8 技術手冊。台灣：碁峰。
- 西內 啟。譯者：陳亦苓 (2015)。統計學，最強的商業武器：實踐篇。台灣：悅知文化
- [Normal Distribution](https://en.wikipedia.org/wiki/Normal_distribution)

## Lincense
[The MIT License](https://opensource.org/licenses/MIT)

### It's Me
[![GitHub](https://i.imgur.com/Z6a4rDG.png?1)](https://github.com/orcahmlee) [![LinkedIn](https://i.imgur.com/ajGoSNq.png?1)](https://www.linkedin.com/in/orcahmlee)
