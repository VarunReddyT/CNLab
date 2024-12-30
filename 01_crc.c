#include <stdio.h>
#include <string.h>

#define N strlen(gen_poly)

char data[28];
char remainder_value[28];
char gen_poly[10];
int data_length, i, j;

void XOR(){
    for (j = 1; j < N; j++)
        remainder_value[j] = ((remainder_value[j] == gen_poly[j]) ? '0' : '1');
}
void crc(){
    for (i = 0; i < N; i++)
        remainder_value[i] = data[i];
    do{
        if (remainder_value[0] == '1')
            XOR();
        for (j = 0; j < N - 1; j++)
            remainder_value[j] = remainder_value[j + 1];
        remainder_value[j] = data[i++];
    } while (i <= data_length + N - 1);
}

void receiver(){
    printf("\nEnter the received data: ");
    scanf("%s", data);
    printf("\nData received: %s\n", data);

    crc();

    for (i = 0; (i < N - 1) && (remainder_value[i] != '1'); i++);
    if (i < N - 1)
        printf("\nError detected");
    else
        printf("\nNo error detected");
}
int main(){
    printf("Enter data to be transmitted: ");
    scanf("%s", data);
    printf("\nEnter the Generating polynomial: ");
    scanf("%s", gen_poly);
    data_length = strlen(data);
    for (i = data_length; i < data_length + N - 1; i++)
        data[i] = '0';
    printf("\nData padded with n-1 zeros : %s", data);

    crc();

    printf("\n\nCRC or Check value is : %s", remainder_value);
    for (i = data_length; i < data_length + N - 1; i++)
        data[i] = remainder_value[i - data_length];
    printf("\n\nFinal data to be sent : %s\n", data);

    receiver();
    return 0;
}