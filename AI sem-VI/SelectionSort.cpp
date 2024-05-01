#include <iostream> 
using namespace std; 

void selectionSort(int arr[], int n) 
{ 
	int min_idx; 
	for (int i = 0; i < n - 1; i++) { 
		min_idx = i; 
		for (int j = i + 1; j < n; j++) { 
			if (arr[j] < arr[min_idx]) 
				min_idx = j; 
		} 
		if (min_idx != i){
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
		}
	} 
} 

int main() 
{ 
    int n;
    cout<<"Enter the size of array:- ";
    cin>>n;
	int arr[n]; 
	cout<<"Enter the "<<n<<" elements in the array:- ";
	for(int i=0;i<n;i++)
	    cin>>arr[i];
	    
	selectionSort(arr, n); 
	cout << "Sorted array: \n"; 
	for(int i=0;i<n;i++)
	    cout<<arr[i]<<" ";
	return 0; 
} 