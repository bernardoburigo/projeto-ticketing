import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Eventos from './pages/Eventos';
import CriarEvento from './pages/CriarEvento';
import EditarEvento from './pages/EditarEvento';
import DetalhesEvento from './pages/DetalhesEvento';
import Relatorios from './pages/Relatorios';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/eventos" element={<Eventos />} />
        <Route path="/eventos/novo" element={<CriarEvento />} />
        <Route path="/eventos/:id/editar" element={<EditarEvento />} />
        <Route path="/eventos/:id" element={<DetalhesEvento />} />
        <Route path="/relatorios" element={<Relatorios />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
