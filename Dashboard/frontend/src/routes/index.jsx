import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from '../pages/Dashboard';
import Login from '../pages/Login';

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        {/* outras rotas aqui */}
      </Routes>
    </Router>
  );
};

export default AppRoutes;
