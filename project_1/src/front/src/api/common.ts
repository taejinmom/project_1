import axios from 'axios';

export const fetchProductById = async (id: string) => {
  const response = await axios.get(`/api/products/${id}`);
  return response.data;
};

export const formatPrice = (price: number): string => {
    return price.toLocaleString('ko-KR') + '원';
  };

export const getAxios = (url: string) => {
    const fullUrl = "http://localhost:8000/api" + url;
    console.log (fullUrl);
    return axios.get(fullUrl);
      
  };

  