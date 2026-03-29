import * as React from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router';
import { GraduationCap, Mail, Lock, ArrowRight } from 'lucide-react';

export function Login() {
  const navegar = useNavigate();
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const lidarComLogin = (e: React.FormEvent) => {
    e.preventDefault();
    // Extrai o nome do email (ex: "joao@email.com" vira "joao")
    const nomeUsuario = email.split('@')[0] || 'Aluno';
    
    // Navega para o painel passando o nome digitado no estado da rota
    navegar('/app', { state: { usuario: nomeUsuario } });
  };

  return (
    <div className="flex h-screen w-full bg-[#050505] font-sans overflow-hidden text-zinc-100 items-center justify-center relative">
      {/* Imagem de Fundo (Compartilhada com o Painel) */}
      <div 
        className="absolute inset-0 z-0 bg-cover bg-center"
        style={{ backgroundImage: `url('https://images.unsplash.com/photo-1631599143424-5bc234fbebf1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx1bml2ZXJzaXR5JTIwY2FtcHVzJTIwYnVpbGRpbmd8ZW58MXx8fHwxNzc0NTA1NDE1fDA&ixlib=rb-4.1.0&q=80&w=1080')` }}
      >
        <div className="absolute inset-0 bg-[#09090b]/85 backdrop-blur-md" />
        {/* Luzes de ambiente */}
        <div className="absolute top-[20%] right-[30%] w-[500px] h-[500px] bg-rose-500/15 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
        <div className="absolute bottom-[20%] left-[30%] w-[400px] h-[400px] bg-indigo-500/15 rounded-full blur-[100px] mix-blend-screen pointer-events-none" />
      </div>

      {/* Caixa de Login em Vidro 3D */}
      <div className="relative z-10 w-full max-w-[420px] mx-4">
        <div className="bg-[#111113]/60 backdrop-blur-3xl border border-white/[0.08] rounded-[2.5rem] p-8 sm:p-10 shadow-[inset_0_1px_2px_rgba(255,255,255,0.1),0_32px_80px_rgba(0,0,0,0.8)] relative overflow-hidden flex flex-col">
          
          {/* Brilhos Internos */}
          <div className="absolute inset-x-0 top-0 h-[2px] bg-gradient-to-r from-transparent via-white/20 to-transparent pointer-events-none" />
          <div className="absolute inset-y-0 left-0 w-[1px] bg-gradient-to-b from-white/10 to-transparent pointer-events-none" />
          <div className="absolute -top-24 -right-12 w-48 h-48 bg-rose-500/20 rounded-full blur-3xl pointer-events-none" />
          <div className="absolute -bottom-24 -left-12 w-48 h-48 bg-indigo-500/10 rounded-full blur-3xl pointer-events-none" />

          {/* Cabeçalho */}
          <div className="flex flex-col items-center justify-center mb-10 relative z-10">
            <div className="w-14 h-14 rounded-2xl bg-gradient-to-tr from-rose-500 to-orange-400 flex items-center justify-center shadow-[0_0_25px_rgba(244,63,94,0.4)] mb-5 border border-white/20">
              <GraduationCap className="w-8 h-8 text-white" />
            </div>
            <h1 className="text-3xl font-bold tracking-tight text-white mb-2">Uniesp</h1>
            <p className="text-zinc-400 text-sm font-medium text-center">Acesse o portal do aluno</p>
          </div>

          {/* Formulário */}
          <form onSubmit={lidarComLogin} className="flex flex-col gap-5 relative z-10">
            <div className="space-y-4">
              {/* Campo Email */}
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
                  <Mail className="h-5 w-5 text-zinc-500" />
                </div>
                <input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  placeholder="Seu e-mail"
                  required
                  className="w-full h-12 bg-white/[0.03] border border-white/10 rounded-xl pl-11 pr-4 text-zinc-100 placeholder:text-zinc-500 focus:outline-none focus:ring-2 focus:ring-rose-500/50 transition-all shadow-inner"
                />
              </div>

              {/* Campo Senha */}
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
                  <Lock className="h-5 w-5 text-zinc-500" />
                </div>
                <input
                  type="password"
                  value={senha}
                  onChange={(e) => setSenha(e.target.value)}
                  placeholder="Sua senha"
                  required
                  className="w-full h-12 bg-white/[0.03] border border-white/10 rounded-xl pl-11 pr-4 text-zinc-100 placeholder:text-zinc-500 focus:outline-none focus:ring-2 focus:ring-rose-500/50 transition-all shadow-inner"
                />
              </div>
            </div>

            {/* Opções Auxiliares */}
            <div className="flex items-center justify-between text-sm px-1 mb-2">
              <label className="flex items-center gap-2 cursor-pointer group">
                <div className="w-4 h-4 rounded border border-white/10 bg-white/[0.03] group-hover:bg-white/10 flex items-center justify-center">
                  <input type="checkbox" className="opacity-0 absolute w-0 h-0" />
                </div>
                <span className="text-zinc-400 group-hover:text-zinc-200 transition-colors">Lembrar-me</span>
              </label>
              <a href="#" className="text-rose-400 hover:text-rose-300 font-medium">Esqueceu a senha?</a>
            </div>

            {/* Botão de Entrar */}
            <button
              type="submit"
              className="group relative w-full h-12 flex items-center justify-center gap-2 bg-gradient-to-r from-rose-500 to-orange-500 hover:from-rose-400 hover:to-orange-400 text-white rounded-xl font-semibold shadow-[0_4px_20px_rgba(244,63,94,0.3)] transition-all active:scale-[0.98] overflow-hidden"
            >
              <span>Entrar no Portal</span>
              <ArrowRight className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
              <div className="absolute inset-0 bg-gradient-to-tr from-white/0 via-white/[0.15] to-white/0 translate-x-[-100%] group-hover:translate-x-[100%] transition-transform duration-700 pointer-events-none" />
            </button>
          </form>

          {/* Rodapé */}
          <div className="mt-8 text-center relative z-10">
            <p className="text-zinc-500 text-sm">
              Ainda não tem acesso?{' '}
              <a href="#" className="text-zinc-300 hover:text-white font-medium border-b border-zinc-700 hover:border-white pb-0.5">
                Primeiro acesso
              </a>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
