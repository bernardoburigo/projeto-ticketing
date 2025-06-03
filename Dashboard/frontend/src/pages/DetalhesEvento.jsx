import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import { useNavigate, useParams } from 'react-router-dom';

const DetalhesEvento = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  // Dados mockados para exibição
  const evento = {
    id,
    nome: 'Festival de Verão',
    data: '2025-07-10',
    local: 'Praça Central',
    ingressos: 500,
    receita: 'R$ 25.000',
    descricao: 'Um festival incrível com várias atrações!',
  };

  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <h1 className="text-2xl font-bold mb-6">
            Detalhes do Evento #{evento.id}
          </h1>

          <div className="bg-white p-6 rounded-2xl shadow space-y-4 max-w-2xl">
            <p><strong>Nome:</strong> {evento.nome}</p>
            <p><strong>Data:</strong> {evento.data}</p>
            <p><strong>Local:</strong> {evento.local}</p>
            <p><strong>Ingressos vendidos:</strong> {evento.ingressos}</p>
            <p><strong>Receita:</strong> {evento.receita}</p>
            <p><strong>Descrição:</strong> {evento.descricao}</p>

            <div className="flex gap-4 pt-4">
              <button
                onClick={() => navigate(`/eventos/${evento.id}/editar`)}
                className="bg-yellow-500 hover:bg-yellow-600 text-white px-4 py-2 rounded-xl"
              >
                Editar
              </button>
              <button
                onClick={() => navigate('/eventos')}
                className="bg-gray-300 hover:bg-gray-400 px-4 py-2 rounded-xl"
              >
                Voltar
              </button>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
};

export default DetalhesEvento;
