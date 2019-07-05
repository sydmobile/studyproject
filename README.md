### RecyclerView

https://blog.csdn.net/xx326664162/article/details/61199895

RecyclerView 的出现是为了代替 ListView 、GridView

#### RecyclerView 基本使用
RecyclerView 比 ListView 更加高级具有灵活性。
从类名上看，RecyclerView 代表：Recycler View，也就是说 RecyclerView 只管回收和复用 View，其他的都是你可以自己去设置。高度解耦，给予我们充分的定制自由（我们可以轻松的实现 ListView、GridView、瀑布流等效果）。

要实现一个 RecyclerView 会接触到几个类，其中 1,2是必须的，剩下的 3,4,5三项可以让 RecyclerView 更好看，效果更好。

- 控制其 item 的排列方式，使用布局管理器 LayoutManager
- 创建一个适配器，使用 RecyclerView.Adapter
- 控制 item 间隔，使用 RecyclerView.ItemDecoration
- 控制 item 增删的动画，使用 RecyclerView.ItemAnimator
- CardView 扩展 FrameLayout类并能够显示卡片内的信息，这些信息在整个平台中拥有一致的呈现方式。CarView 小部件可以拥有阴影和圆角。


1. RecyclerView 基本用法

2. 封装一个基本的 Adapter 

3. 介绍一下分隔线 itemDecorView



关于 position 

1- position 在 onBindViewHolder 方法中的

这个位置用来绑定数据
比如：
public void  onBindViewHolder(ViewHolder viewholder,int position){
    holder.tvName.setText(strings.get(position));
    
}

但是不能用于 
public void onBindViewHolder(ViewHolder viewHolder,int position){
    holder.tvName.setOnClickListener(new View.OnClickListener(){
    
        public void onClick(View view){
            Log.e("==",position+"");
        
        }
    });
}
这样当你插入或者删除数据调用  adapter.notifyIteminset() 的时候，是不会重新 onBindViewHolder 的，只会插入新的 View 的调用一次。
这个时候。当你点击之前的 item 的时候，位置还是之前的 position 。就出现数据错乱了。

至于 viewHolder.getAdapterPosition 和 viewHolder.getLayoutPosition 。他们在大多数情况下一样的。只是这里有个等待的问题。RecyclerView 是要等着
adapter 里面的数据更改完，然后再体现在布局上面（<16ms）。所以这么短的时间我们一般感不到差别。
AdapterPosition 是最先发生变化的。LayoutPosition 是我们看到的。

建议用 getAdapterPosition 绑定数据。 getLayoutPosition 告诉用户按的是那个 item。





  /**
     * 如果 参数不为空的时候更新
     *
     * @param data 数据
     */
    public void updateData(List<T> data) {
        if (data != null && data.size() > 0) {







   @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = callBack.getView(position, convertView, parent, this);
        return convertView;
    }
    

    public interface CallBack {
        View getView(int position, View converView, ViewGroup parent, AdapterCommon adapterCommon);
    }



    /**
     * 增加数据
     *
     * @param data 数据
     */
     
    public void addData(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    
    CustomItemDecoration 的 onDraw 和 onDrawOver 方法不断的执行，一滑动 RecyclerView 就执行好多次，所以这里面要尽可能优化。
    
  ![关系图](来自网络)
  
  Android 提供了两种方式来绘制图形到 view 上面。
  
  Android UI 开发中，View 作为其基本元素，改变 View 图形显示的 2D 绘画有两种方法
  
  - 调用 View 及其子类中提供的方法：
    如：View：setBackground(Drawable),ImageView:setImageBitmap(Bitmap) 这些方法直接或者间接设置相应类中的 Drawable 私有成员，当时最终还是落在 canvas 中
    
  - 继承 View 并重写 onDraw(Canvas)，回调中得到画布 Canvas，调用其一系列基本图形的绘画 draw 方法操作其 Bitmap 中的像素数据。
  
  Bitmap 是一种像素存储介质，图像最终要保存下来要通过它。Canvas 则是绘制工具，提供了 2D 的绘图方法，方便我们来绘图。View 是一种容器，装载着 Bitmap，将其呈现出来。Drawable：Android 把可绘制的图形抽象成了 Drawable，封装了一系列绘图时需要的工作（角度、长度、图形、颜色），最后 draw() 方法中用
  绘制到 Canvas 上。最终还是要经过 Canvas 的。Drawable 更像是在 Canvas 上面又封装了一层。
  
  Drawable 的类型：ColorDrawable、GradientDrawable、BitmapDrawable、NinePatchDrawable、InsetDrawable、ClipDrawble、ScaleDrawable、RotateDrawable、AnimationDrawable、LayerDrawable、LevelListDrawable、StateListDrawable、TransitionDrawable
  
  #### ColorDrawable
  ColorDrawable 是最简单的 Drawable。包装一种固定的颜色，当 ColorDrawable 绘制到 Canvas 上的时候，使用颜色填充 Paint，在 Canvas 上绘制一块单色区域。
  ```xml
        <?xml version="1.0" encoding="utf-8" ?>
        <color xmls:android = "http"
            android:color = "#FFFFFF">
            
</color>
    
  ```
  使用 color 根节点来创建 ColorDrawable。它只有一个属性 color 属性。
  也可以使用代码来创建 ColorDrawable 
  // 必须指明透明度，不然默认透明
  ColorDrawable drawable = new ColorDawable(0xFFFF0000)
  
  #### GradientDrawable
  
  GradientDrawable 表示一个渐变区域，可以实现线性渐变、发散渐变和扫描渐变效果。
  在 xml 中使用 shape 作为根节点来创建 GradientDrawable，它包含很多属性和子节点：
  
  shape 属性指定形状的：
  取值：
  - rectangle：矩形，默认形状，可以画出矩形、圆角矩形、弧形等
  - oval：椭圆，可以画正圆
  - line：线性，画实线和虚线
  - ring：环形，可以画环形进度条
  
  特定的属性，适用于 android:shape 属性指定为 ring 的时候生效
  - android:innerRadius 内环半径
  - android:innerRadiusRatio 浮点型，用环的宽度的比率来表示内环半径，默认为 3 ，表示内环半径为环的宽度除以 3 ，该值会被 android:innerRadius 覆盖。
  - android:thickness 环的宽度
  - android:thicknessRatio 浮点型，以环的宽度比率来表示的厚度，默认 9 ，表示环的厚度为环的宽度除以 9 ，会被 android:thickness 覆盖。
  - android:useLevel 一般为 false，否则可能环形无法显示，只有作为 LevelListDrawable 使用时才设为 true。
  子节点:
  - solid:设置形状填充的颜色，只有 color 属性
  - padding 设置内容与形状边界的内间距
  - gradient：设置形状的渐变色，可以是线性渐变、辐射渐变、扫描渐变
    - android:type 渐变的类型 linear 默认，线性渐变；  radial 放射渐变,设置这种类型的话，gradientRadius 必须设置；sweep 扫描渐变
    - android:startColor 渐变开始的颜色
    - android:endColor 渐变结束的颜色
    - android:centerColor 渐变中间的颜色
    - android:angle 渐变角度，线程渐变的时候才会有效，必须是 45 的倍数，0 表示从左右， 90 表示从下到上 （逆时针转）
    - android:centerX 渐变中心的相对 X 坐标，放射渐变时才有效，在 0.0~1.0 之间，默认 0.5 表示正中间。
    - android:centerY 同上
    - android:gradientRadius 渐变的半径，只有是 radial 类型才有效
    - android:useLevel 如果为 true 则可以在 LevelListDrawable 中使用。
  
  - corners:设置圆角，只适用于 rectangle 类型，可分别设置四个角不同半径的圆角，当设置的圆角半径很大时，比如 200 dp，就变成弧形边了。
  - stroke:设置描边，可描成实线或者虚线  
   
    - android:color 描边的颜色
    - android:width 描边的宽度
    - android:dashWidth 设置虚线时的横线长度
    - android:dashGap 设置虚线间隔
    
  - size 设置形状默认的大小，可设置宽度和高度
  
    
  
  通过 Drawable 和 Canvas
  
 
  如 图片、图形、动画等。适合一些简单不需要动态改变的图形，比如：动画、shape 等，也可以自定义一些 drawable 对象。
  Drawable 是可绘制图形的抽象，可以定义各种可绘制图形，包括：ShapeDrawable、BitmapDrawable 等等。
  
  Drawable 在 View 2D 绘画里的一个很重要的抽象类，抽象出了**怎么画，画什么**的一个概念，并提供了一些子类可能需要的控制绘画状态的回调方法如：onLevelChange(int),onStateChange(int[] state)
  
  Drawable 的子类表示不同的绘画模式，如 BitmapDrawable 用于展示图片，ShapeDrawable 用于绘制不同类型的基本图形，StateListDrawable 利用父类的 onStateChange(int []) 回调用作根据状态显示不同图形等等。
  
  其中它有一个重要的方法，其子类必须实现这个方法，把图形传画到传入的 Canvas 上。
  
  ```java
        
        public abstract void draw(Canvas canvas);
  ```
  我们一般很少直接接触到 Drawable ，大多数情况下是通过 drawable 资源的方式获得，间接使用。
  
  #### Drawable 与 View 的关系
  
  每个 View 都至少有一个 Drawable 成员变量，因为作为基类的 View 有一个 Drawable 类型的私有成员 mBackground 用作背景绘画。
  ```java
        public class View implements Drawable.Callback,X,X{
            
            ``` ```;
            ``` ```;
            private Drawable mBackground;
            
            public void setBackground(Drawable background){
                setBackgroundDrawable(background);
            }
            
            public void setBackgroundDrawable(Drawable background){
                .....
                mBackground = background;
                .....;
            }
            
            public void draw(Canvas canvas){
                ``` ```;
                if(!dirtyOpaaque){
                    drawBackground(canvas);
                }
                ``` ```;
            }
            
            public void drawBackground(Canvas canvas){
                final Drawable background = mBackground;
                ``` ```;
                background.draw(canvas);
                ``` ```;
            }
            
            @Override
            public void onDraw(Canvas canvas){
                
            }
    
        }

```

  ImageView 的显示主图也是一个 Drawable
  ```java
    
        public class ImageView extends View{
            // 就是 ImageView 中的 img
            private Drawable mDrawable;
            
            public void setImageDrawable(@Nullable Drawable drawable){
                // ''''';
                updateDrawable(drawable);
                // ''''';
            }
            
            private void updateDrawable(Drawable d){
                //'''''';
                
                mDrawable = d;
                //'''''';
                
            }
            
            @Override
            protected void onDraw(Canvas canvas){
                //----;
                mDrawable.draw(canvas);
                //----;
            }
    
        }

  ```
  最终是将 Drawable 中的内容绘制到了 Canvas 上。
  
  #### Canvas 和 Bitmap
  
  Canvas 虽然直译为画布，但是它实际操作和存储的像素数据都在他的私有成员 Bitmap 对象中。Canvas 只提供了一系列基本图形的绘制方法，如：drawBitmap、drawRect等等。
  每个 Canvas 都必须传入一个 Bitmap。
  
  
  三种方式定义 Drawable 
  
  ### 1.将 Drawable（包括图片、动画等）绘制到 View 对象
  
  #### 1 使用图片
  图片适用于应用程序图标、logo、icon 等
  
  如果想要将文件作为 bit 流处理，则要放在 res/raw 中
  ```java
     
     Resources res = mContext.getResources();
     Drawable myImages = res.getDrawable(R.drawble.my_img);
  ```
  项目中每个唯一的资源都只有一个状态，如果同一图片实例化为两个 Drawable 对象，更改其中一个也会影响另一个。
  
  #### 2 使用 xml 定义 Drawable 属性
  
  在 res/drawable 目录中创建 xml 文件，通过 Resource.getDrawable()根据 ID 来检索和实例化对象。
 任何支持 inflate() 方法的 Drawable 的子类都可以在 xml 中定义。shape、inset、animator 等等。
 
 #### 3 代码中创建
 
 ```java
    // 这里是绘制的 ShapeDrawable，当然还有很多比如：BitmapDrawable、InsetDrawable、NinePatchDrawable 等等
    public class CustomDrawable extends View{
        private ShapeDrawable mDrawable;
        
        public CustomDrawable(Context context){
            super(context);
            int x = 10,y = 10,width = 300,height = 50;
            // 传入 OvalShape 表示椭圆形
            mDrawable = new ShapeDrawable(new OvalShape());
            // 设置颜色，不设置则为黑色
            mDrawable.getPaint().setColor(0xFF74AC23);
            // 设置 bounds 不设置就不会被绘制
            mDrawable.setBounds(x,y,x+width,y+height);
        }
        
        protected void onDraw(Canvas canvas){
            mDrawable.draw(canvas);
        }
    
    
    }
 
 
 ```
 ### 使用 canvas 的各种 draw 方法来绘制图形
 
 适用于需要定期重新绘制的图形，比如游戏画面，一些需要动态改变的图形，而且此时也可以控制动画。对于这种方式常用的方法有，在 UI 线程中通过 invalidate() 让系统回调 onDraw() 实现；或者在子线程中在 SurfaceView 绘制。
 
 通过 canvas 绘制图形时，绘制的图其实是在底层 bitmap 上的，我们可以在 onDraw() 中直接绘制或者在 SurfaceView 中通过 SurfaceHolder.lockCanvas() 获取 Canvas。但是如果创建一个新的 Canvas 的话，则需要定义新一个 Bitmap。之前的可以直接绘制，因为在 View 中已经设置好了 Bitmap 了。
 
 ```java
    Bitmap b = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(b);
```
 而这个 Bitmap 也可以绘制到其他 canvas 上。
 
 Canvas 有一系列的 draw 方法，但是其他类也有一些 draw 方法。例如Drawable 有自己的 draw 方法，用于将自身绘制到 canvas 上。
 
 #### 在 View 上绘制
 如果不需要大量的计算和帧速率，应当考虑自定义 View。继承 View 或者其子类，定义 onDraw 方法，系统会调用它来绘制图形。在需要时，调用 invalidate 方法，表示想要重绘，当时系统并不保证及时的绘制，在子线程中使用 postInvalidate 方法。
 
 #### 在 SurfaceView 上绘制
 SurfaceView 是 View 的特殊子类，在 View 层次结构中提供了一个专用绘图面。目的是在子线程进程绘制，而不用等待 onDraw()。首先要继承 SurfaceView，并实现 SurfaceView.Callback，用于通知关于底层 Surface 信息。以便知道何时开始绘制。
 
 
 参考：https://blog.csdn.net/dapangzao/article/details/70228541
 
 ## Canvas 和 Drawable
 谷歌官方文档：http://developer.android.com/intl/zh-cn/guide/topics/graphics/2d-graphics.html
 
 其实所有的绘制最终都是要落在 Canvas 对象上面的。Canvas 类拥有一套绘图方法供用户来使用，如：drawBitmap、drawRect、drawText 等等。其他的类也有 draw 方法，但最终都是需要有 Canvas。比如用户可能需要在 Canvas 上添加一些 Drawable 对象。Drawable 对象就有自己的 draw 方法，此时 Canvas 作为参数被传入
 
 
  