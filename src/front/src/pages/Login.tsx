
import { Box, Button, TextField, Typography } from '@mui/material';

const Login = () => {
  return (
    <Box sx={{ maxWidth: 400, mx: 'auto', mt: 10 }}>
      <Typography variant="h5" mb={2}>Login</Typography>
      <TextField fullWidth label="Email" margin="normal" />
      <TextField fullWidth label="Password" type="password" margin="normal" />
      <Button variant="contained" fullWidth sx={{ mt: 2 }}>Login</Button>
    </Box>
  );
};

export default Login;
