import { useIsFocused, useNavigation } from '@react-navigation/native';
import React, { useEffect, useState } from 'react'
import { StyleSheet, ScrollView, Alert, Text } from "react-native";
import { TouchableWithoutFeedback } from 'react-native-gesture-handler';
import { fetchOrders } from '../api';
import Header from '../header';
import OrderCard from '../orderCard';
import OrderDetails from '../orderDetails'
import { Order } from '../types';

export default function Orders() {
    const [orders, setOrders] = useState<Order[]>([]);
    const [isLoading, setIsLoading] = useState(false);
    const navigation = useNavigation();
    const isFocused = useIsFocused();

    const fetchData = () => { 
        setIsLoading(true);
        fetchOrders()
        .then(response => setOrders(response.data))
        .catch(() => Alert.alert('Huve um erro ao buscar os pedidos'))
        .finally(() => setIsLoading(false))
    }

    useEffect(() => {
        if(isFocused) {
            fetchData();
        }
    }, [isFocused])


    const handleOnPress = (order: Order) => {
        navigation.navigate('OrderDetails', {
            order
        });
    }

    return(
        <>
            <Header/>
            <ScrollView style = {styles.container}>
                {isLoading ? (
                    <Text style = {styles.text}>
                        Buscando pedidos...
                    </Text>
                ) : (
                    orders.map(order => (
                        <TouchableWithoutFeedback 
                        key = {order.id} 
                        onPress = {() => handleOnPress(order)}
                        >
                            <OrderCard order = {order}/>
                        </TouchableWithoutFeedback>
                    ))
                )}
            </ScrollView>
        </>
    );
}

const styles = StyleSheet.create({
    container: {
        paddingRight:'5%',
        paddingLeft: '5%'
    },
    text: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        margin: '20%',
        padding: 10,
        backgroundColor: 'white',
        shadowOpacity: 0.25,
        shadowColor: 'black',
        textShadowOffset: {width: 0, height: 4},
        shadowRadius: 20,
        borderRadius: 10,
        elevation: 5,
        color: '#DA5C5C'        
    }
})