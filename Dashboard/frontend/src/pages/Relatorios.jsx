import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import { useState } from 'react';
import { FaFileCsv, FaFilePdf } from 'react-icons/fa';

const eventos = ['Festival de Música', 'Congresso Tech', 'Workshop de Design'];
const publicos = ['Jovens', 'Adultos', 'VIP'];
const statusCheckin = ['Todos', 'Com Check-in', 'Sem Check-in'];

const dadosRelatorio = [
  { nome: 'João Silva', publico: 'Jovens', evento: 'Festival de Música', checkin: true },
  { nome: 'Maria Oliveira', publico: 'VIP', evento: 'Congresso Tech', checkin: false },
  { nome: 'Carlos Souza', publico: 'Adultos', evento: 'Workshop de Design', checkin: true },
  { nome: 'Ana Lima', publico: 'Adultos', evento: 'Congresso Tech', checkin: false },
];

const Relatorios = () => {
  const [eventoSelecionado, setEventoSelecionado] = useState('Todos');
  const [publicoSelecionado, setPublicoSelecionado] = useState('Todos');
  const [checkinSelecionado, setCheckinSelecionado] = useState('Todos');

  const dadosFiltrados = dadosRelatorio.filter(item => {
    const eventoOk = eventoSelecionado === 'Todos' || item.evento === eventoSelecionado;
    const publicoOk = publicoSelecionado === 'Todos' || item.publico === publicoSelecionado;
    const checkinOk =
      checkinSelecionado === 'Todos' ||
      (checkinSelecionado === 'Com Check-in' && item.checkin) ||
      (checkinSelecionado === 'Sem Check-in' && !item.checkin);

    return eventoOk && publicoOk && checkinOk;
  });

  const limparFiltros = () => {
    setEventoSelecionado('Todos');
    setPublicoSelecionado('Todos');
    setCheckinSelecionado('Todos');
  };

  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <h1 className="text-2xl font-bold mb-4">Relatórios</h1>

          <div className="bg-white p-6 rounded-xl shadow mb-6">
            <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-4">
              <select
                value={eventoSelecionado}
                onChange={e => setEventoSelecionado(e.target.value)}
                className="border rounded p-2"
              >
                <option value="Todos">Todos os Eventos</option>
                {eventos.map(e => (
                  <option key={e} value={e}>
                    {e}
                  </option>
                ))}
              </select>

              <select
                value={publicoSelecionado}
                onChange={e => setPublicoSelecionado(e.target.value)}
                className="border rounded p-2"
              >
                <option value="Todos">Todos os Públicos</option>
                {publicos.map(p => (
                  <option key={p} value={p}>
                    {p}
                  </option>
                ))}
              </select>

              <select
                value={checkinSelecionado}
                onChange={e => setCheckinSelecionado(e.target.value)}
                className="border rounded p-2"
              >
                {statusCheckin.map(status => (
                  <option key={status} value={status}>
                    {status}
                  </option>
                ))}
              </select>

              <button
                onClick={limparFiltros}
                className="bg-red-500 text-white rounded p-2 hover:bg-red-600"
              >
                Limpar Filtros
              </button>
            </div>

            {dadosFiltrados.length === 0 ? (
              <p className="text-center text-gray-500">Nenhum resultado encontrado.</p>
            ) : (
              <table className="w-full border">
                <thead>
                  <tr className="bg-gray-200">
                    <th className="border p-2">Nome</th>
                    <th className="border p-2">Público</th>
                    <th className="border p-2">Evento</th>
                    <th className="border p-2">Check-in</th>
                  </tr>
                </thead>
                <tbody>
                  {dadosFiltrados.map((item, index) => (
                    <tr key={index}>
                      <td className="border p-2">{item.nome}</td>
                      <td className="border p-2">{item.publico}</td>
                      <td className="border p-2">{item.evento}</td>
                      <td className="border p-2">
                        {item.checkin ? 'Realizado' : 'Não realizado'}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            )}

            <div className="flex gap-5 mt-5">
              <button className="bg-blue-500 text-white rounded p-2 flex items-center gap-2 hover:bg-blue-600">
                <FaFileCsv /> Exportar CSV
              </button>
              <button className="bg-red-500 text-white rounded p-2 flex items-center gap-2 hover:bg-green-600">
                <FaFilePdf /> Exportar PDF
              </button>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
};

export default Relatorios;