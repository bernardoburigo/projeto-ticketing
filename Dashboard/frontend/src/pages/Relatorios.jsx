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

  const filtrarDados = dadosRelatorio.filter(item => {
    const eventoMatch = eventoSelecionado === 'Todos' || item.evento === eventoSelecionado;
    const publicoMatch = publicoSelecionado === 'Todos' || item.publico === publicoSelecionado;
    const checkinMatch =
      checkinSelecionado === 'Todos' ||
      (checkinSelecionado === 'Com Check-in' && item.checkin) ||
      (checkinSelecionado === 'Sem Check-in' && !item.checkin);

    return eventoMatch && publicoMatch && checkinMatch;
  });

  const exportarCSV = () => {
    const linhas = [
      ['Nome', 'Ingresso', 'Evento', 'Valor'],
      ...dadosRelatorio.map(item => [item.nome, item.ingresso, item.evento, item.valor]),
    ];
    const csvContent =
      'data:text/csv;charset=utf-8,' +
      linhas.map(e => e.join(',')).join('\n');
    const encodedUri = encodeURI(csvContent);
    const link = document.createElement('a');
    link.setAttribute('href', encodedUri);
    link.setAttribute('download', 'relatorio.csv');
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  const exportarPDF = () => {
    alert('Funcionalidade de exportar para PDF será implementada futuramente.');
    // Aqui você poderá usar uma lib como jspdf ou pdfmake para gerar o PDF.
  };

  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <h1 className="text-2xl font-bold mb-6">Relatórios</h1>

          {/* Filtros */}
          <div className="bg-white p-6 rounded-xl shadow mb-6 grid grid-cols-1 md:grid-cols-3 gap-4">
            <div>
              <label className="block mb-2 font-medium">Evento</label>
              <select
                className="w-full border rounded px-3 py-2"
                value={eventoSelecionado}
                onChange={e => setEventoSelecionado(e.target.value)}
              >
                <option>Todos</option>
                {eventos.map(ev => (
                  <option key={ev}>{ev}</option>
                ))}
              </select>
            </div>

            <div>
              <label className="block mb-2 font-medium">Público-Alvo</label>
              <select
                className="w-full border rounded px-3 py-2"
                value={publicoSelecionado}
                onChange={e => setPublicoSelecionado(e.target.value)}
              >
                <option>Todos</option>
                {publicos.map(pub => (
                  <option key={pub}>{pub}</option>
                ))}
              </select>
            </div>

            <div>
              <label className="block mb-2 font-medium">Status Check-in</label>
              <select
                className="w-full border rounded px-3 py-2"
                value={checkinSelecionado}
                onChange={e => setCheckinSelecionado(e.target.value)}
              >
                {statusCheckin.map(status => (
                  <option key={status}>{status}</option>
                ))}
              </select>
            </div>
          </div>

          {/* Tabela */}
          <div className="bg-white p-6 rounded-xl shadow mb-6">
            <h2 className="text-lg font-semibold mb-4">Dados Filtrados</h2>
            <table className="w-full table-auto border-collapse">
              <thead>
                <tr className="bg-gray-200">
                  <th className="border px-4 py-2 text-left">Nome</th>
                  <th className="border px-4 py-2 text-left">Evento</th>
                  <th className="border px-4 py-2 text-left">Público-Alvo</th>
                  <th className="border px-4 py-2 text-left">Check-in</th>
                </tr>
              </thead>
              <tbody>
                {filtrarDados.map((item, index) => (
                  <tr key={index}>
                    <td className="border px-4 py-2">{item.nome}</td>
                    <td className="border px-4 py-2">{item.evento}</td>
                    <td className="border px-4 py-2">{item.publico}</td>
                    <td className="border px-4 py-2">
                      {item.checkin ? 'Sim' : 'Não'}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          {/* Botões de Exportação */}
          <div className="flex gap-4">
            <button
              onClick={exportarCSV}
              className="flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
            >
              <FaFileCsv /> Exportar CSV
            </button>
            <button
              className="flex items-center gap-2 bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700"
              disabled
            >
              <FaFilePdf /> Exportar PDF
            </button>
          </div>
        </main>
      </div>
    </div>
  );
};

export default Relatorios;
