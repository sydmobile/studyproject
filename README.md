### RecyclerView

https://blog.csdn.net/xx326664162/article/details/61199895

RecyclerView 的出现是为了代替 ListView 、GridView

#### RecyclerView 基本使用
RecyclerView 比 ListView 更加高级具有灵活性。
从类名上看，RecyclerView 代表：Recycler View，也就是说 RecyclerView 只管回收和复用 View，其他的都是你可以自己去设置。高度解耦，给予我们充分的定制自由（我们可以轻松的实现 ListView、GridView、瀑布流等效果）。

https://blog.csdn.net/xx326664162/article/details/61199895






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
    
  