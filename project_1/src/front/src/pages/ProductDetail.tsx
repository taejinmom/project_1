
import { Box, Typography, Button } from '@mui/material';
import { getAxios } from '../api/common';


const ProductDetail = () => {
  const test = async () => {
    try{
      const response = await getAxios("/ping");
      alert(`서버 응답: ${response.data}`);
    } catch (error) {
      alert('통신 오류!');
      console.error(error);
    }
  };
  return (
    <Box sx={{ maxWidth: 600, mx: 'auto', mt: 10 }}>
      <img src="https://via.placeholder.com/600x400" alt="Product" style={{ width: '100%' }} />
      <Typography variant="h4" mt={2}>Basic T-Shirt</Typography>
      <Typography variant="h6" color="textSecondary" mt={1}>$25</Typography>
      <Typography mt={2}>High-quality cotton t-shirt for everyday wear.</Typography>
      <Button variant="contained" sx={{ mt: 3 }} onClick={test}>Add to Cart</Button>
    </Box>
  );
};

export default ProductDetail;
