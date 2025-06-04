import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Relatorios from './pages/Relatorios';
import Eventos from './pages/Eventos';
import CriarEvento from './pages/CriarEvento';
import EditarEvento from './pages/EditarEvento';
import DetalhesEvento from './pages/DetalhesEvento';
import Promotores from './pages/Promotores';
import CriarPromotor from './pages/CriarPromotor';
import EditarPromotor from './pages/EditarPromotor';
import DetalhesPromotor from './pages/DetalhesPromotor';

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
        <Route path="/promotores" element={<Promotores/>} />
        <Route path="/promotores/novo" element={<CriarPromotor/>} />
        <Route path="/promotores/:id/editar" element={<EditarPromotor/>} />
        <Route path="/promotores/:id" element={<DetalhesPromotor />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
