#include <iostream>

using namespace std;
//����ָ���ʹ��

void f(int **);
int main(void)
{
    int *p; //p�Ǹ�ָ�������int *��ʾ��p����ֻ�ܴ洢int���ͱ����ĵ�ַ
    int i = 10;
    int j;
   //j = *p;
    //cout << j << endl;   //���д���


    p = &i; //�� i �ĵ�ַ���͸� p��p������i�ĵ�ַ��p��ָ����i
    j = *p; //�ȼ��� j = i
    //*P = i; //�ȼ��� i = i
    //p = 10;  //error��10���������ǵ�ַ0.

    cout<<"\n=====================\n"<<endl;
    cout<<"���ͨ�������޸�ʵ�ε�ֵ\n"<<endl;

    int k = 9;
    int *kl = &k;

    cout<<kl<<endl;
    f(&kl);
    cout<<kl<<endl;


    return 0;
}

//���ͨ�������޸�ʵ�ε�ֵ������ȫ��
void f(int **q) {
    *q = (int *)0xFFFFFFF;
}

