import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import { useNavigate, useParams } from 'react-router-dom';

const DetalhesPromotor = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const evento = {
    id,
    nome: 'Victor Pereira',
    codigo: 'victorp',
    email: 'contato@victorp.com.br',
    telefone: '(48) 98765-4321',
    eventosRelacionados: 'Festival de Música',
    ingressosVendidos: 120,
    receita: 4800,
  };

  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <h1 className="text-2xl font-bold mb-6">Detalhes do Promotor</h1>
          <div className="bg-white p-6 rounded-xl shadow">
            <div className="space-y-4">
              <p><strong>Nome:</strong> {evento.nome}</p>
              <p><strong>Código:</strong> {evento.codigo}</p>
              <p><strong>Email:</strong> {evento.email}</p>
              <p><strong>Telefone:</strong> {evento.telefone}</p>
              <p><strong>Evento Relacionado:</strong> {evento.eventosRelacionados}</p>
              <p><strong>Ingressos Vendidos:</strong> {evento.ingressosVendidos}</p>
              <p><strong>Receita:</strong> R$ {evento.receita}</p>
            </div>

            <button
              className="mt-6 bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-xl"
              onClick={() => navigate('/promotores')}
            >
              Voltar
            </button>
          </div>
        </main>
      </div>
    </div>
  );
};

export default DetalhesPromotor;
