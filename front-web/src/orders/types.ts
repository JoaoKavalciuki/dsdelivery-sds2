export type Product = {
    id: number
    name: string;
    description: string; 
    imageUri: string;
    price: number;
}

export type OrderLocationData = {
    latitude: number,
    longitude: number,
    address: string;
}