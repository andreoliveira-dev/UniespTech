import * as React from 'react';
import { useLocation } from 'react-router';
import { BarraLateral } from '../components/BarraLateral';
import { PainelPrincipal } from '../components/PainelPrincipal';

export function Painel() {
  const localizacao = useLocation();
  const nomeUsuario = localizacao.state?.usuario;

  return (
    <div className="flex h-screen w-full bg-[#050505] font-sans overflow-hidden text-zinc-100 relative">
      <div 
        className="absolute inset-0 z-0 bg-cover bg-center"
        style={{ backgroundImage: `url('https://images.unsplash.com/photo-1631599143424-5bc234fbebf1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx1bml2ZXJzaXR5JTIwY2FtcHVzJTIwYnVpbGRpbmd8ZW58MXx8fHwxNzc0NTA1NDE1fDA&ixlib=rb-4.1.0&q=80&w=1080')` }}
      >
        <div className="absolute inset-0 bg-[#09090b]/85 backdrop-blur-md" />
        <div className="absolute top-[10%] right-[15%] w-[600px] h-[600px] bg-rose-500/15 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
        <div className="absolute bottom-[10%] left-[20%] w-[500px] h-[500px] bg-indigo-500/15 rounded-full blur-[100px] mix-blend-screen pointer-events-none" />
      </div>

      <div className="relative z-10 flex w-full h-full">
        <BarraLateral />
        <main className="flex-1 relative overflow-hidden flex items-center justify-center p-6 md:p-10 xl:p-16">
          <div className="relative z-10 w-full h-full max-w-[1600px] flex items-stretch">
            <PainelPrincipal nomeUsuario={nomeUsuario} />
          </div>
        </main>
      </div>
    </div>
  );
}
