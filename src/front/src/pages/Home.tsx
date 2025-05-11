
import { Box, Typography, Button } from '@mui/material';
import axios from 'axios';
import { Link } from 'react-router-dom';

const Home = () => {

  return (
    <Box sx={{ textAlign: 'center', py: 10, backgroundColor: '#fff' }}>
      <Typography variant="h3" gutterBottom>Style Shop</Typography>
      <Typography variant="h6" gutterBottom>Your style, your fashion.</Typography>
      <Button variant="outlined" component={Link} to="/products">Shop Now</Button>
    </Box>
    
  );
};

export default Home;
