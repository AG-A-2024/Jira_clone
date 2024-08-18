'use client'
import { useState, useEffect } from 'react';

// Interfejs definiujący strukturę danych zwracanych przez API
interface ProductData {
  name: string;
  price: string;
  description: string;
  // Dodaj więcej pól według potrzeb
}

export default function Component() {
  const [jsonData, setJsonData] = useState<ProductData | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('https://dummyjson.com/products/1');
        const data: ProductData = await response.json();
        setJsonData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      {jsonData ? (
        <div>
          <h2>Product Details</h2>
          <p>Name: {jsonData.name}</p>
          <p>Price: {jsonData.price}</p>
          <p>Description: {jsonData.description}</p>
          {/* Dodaj więcej pól według potrzeb */}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}
