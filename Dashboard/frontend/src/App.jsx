import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Eventos from './pages/Eventos';
import CriarEvento from './pages/CriarEvento';
import EditarEvento from './pages/EditarEvento';
import DetalhesEvento from './pages/DetalhesEvento';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/eventos" element={<Eventos />} />
        <Route path="/eventos/novo" element={<CriarEvento />} />
        <Route path="/eventos/:id/editar" element={<EditarEvento />} />
        <Route path="/eventos/:id" element={<DetalhesEvento />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
