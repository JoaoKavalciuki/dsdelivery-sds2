import React from 'react';
import { useNavigation } from "@react-navigation/native";
import { StyleSheet, View, Text, Alert, Linking } from "react-native";
import Header from '../header';
import { Order } from '../types';
import OrderCard from '../orderCard';
import { RectButton } from 'react-native-gesture-handler';
import { confirmDelivered } from '../api';

type Props = {
    route: {
        params: {
            order: Order;
        }
    }
}

export default function OrderDetails({route}: Props) {
    const {order} = route.params;
    const navigation = useNavigation();

    const handleOnCancel = () => {
        navigation.navigate('Orders');
    }

    const handleConfirmDelivery = () => {
        confirmDelivered(order.id)
        .then(() => {
            Alert.alert(`Pedido ${order.id} entregue com sucesso!`);
            navigation.navigate('Orders');
        })
        .catch(() => {
            Alert.alert(`Ocorreu um erro ao confirmar a entrega do pedido ${order.id}`);
        });
    }

    const startNavigation = () => {
        Linking.openURL(`https://www.google.com/maps/dir/?api=1&travelmode=driving&dir_action=navigate&destination=${order.latitude},${order.longitude}`);

    }

    return(
        <>
            <Header></Header>
            <View  style = {styles.container}>
                <OrderCard order = {order}/>
                <RectButton style = {styles.button}>
                    <Text style = {styles.buttonText} onPress = {startNavigation}>
                        INICIAR NAVEGAÇÃO
                    </Text>
                </RectButton>
                <RectButton style = {styles.button}>
                    <Text style = {styles.buttonText} onPress = {handleConfirmDelivery}>
                        CONFIRMAR ENTREGA
                    </Text>
                </RectButton>
                <RectButton style = {styles.button}>
                    <Text style = {styles.buttonText} onPress = {handleOnCancel}>
                        CANCELAR
                    </Text>
                </RectButton>
            </View>
        </>
    );
}

const styles = StyleSheet.create({
    container: {
        paddingRight: '5%',
        paddingLeft: '5%'
      },
      button: {
        backgroundColor: '#DA5C5C',
        flexDirection: 'row',
        borderRadius: 10,
        marginTop: 40,
        alignItems: 'center',
        justifyContent: 'center'
      },
      buttonText: {
        paddingTop: 15,
        paddingBottom: 15,
        paddingLeft: 50,
        paddingRight: 50,
        fontWeight: 'bold',
        fontSize: 18,
        color: '#FFF',
        letterSpacing: -0.24,
        fontFamily: 'OpenSans_700Bold'
      }
});