
public class MergeSort {
    /**
     * Runs the program of merging the array
     * @param array the array to be sorted
     */
    public static void run(SortArray array) {
        SortArray aux=new SortArray(true);
        Visualizer.addArray(aux);
        sort(array,0,array.data().length-1,aux);
        Visualizer.removeArray(aux);
        array.unHighlightAll();
        array.update();
        Visualizer.running=false;
    }

    public static void sort(SortArray array, int left, int right, SortArray aux) {
        if(right<=left) return;
        array.update();
        aux.update();
        int mid=(left+right)/2;
        sort(array,left,mid,aux);
        sort(array,mid+1,right,aux);
        merge(array,left,mid,right,aux);
    }

    public static void merge(SortArray array, int left, int mid, int right, SortArray aux) {
        int i=left;
        int j=mid+1;
        for(int k=left;k<=right;k++)
            aux.data()[k].setValue(array.data()[k].val());
        aux.update();
        for(int k=left;k<=right;k++) {
            array.data()[k].highlight();
            array.update();
            if(i>mid) {
                array.data()[k].setValue(aux.data()[j].val());
                aux.data()[j].highlight();
                aux.update();
                j++;
            }else if(j>right) {
                array.data()[k].setValue(aux.data()[i].val());
                aux.data()[i].highlight();
                aux.update();
                i++;
            }else if(aux.data()[j].val()<aux.data()[i].val()) {
                array.data()[k].setValue(aux.data()[j].val());
                aux.data()[j].highlight();
                aux.update();
                j++;
            }else {
                array.data()[k].setValue(aux.data()[i].val());
                aux.data()[i].highlight();
                aux.update();
                i++;
            }
            array.data()[k].unHighlight();
            aux.unHighlightAll();
        }
        array.update();
    }
}
