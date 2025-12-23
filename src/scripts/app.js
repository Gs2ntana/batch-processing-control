async function capturarJson(url){
    try{
        const response = await fetch(url);
        return response.json();
    }catch (error){
        console.error(error.massage);
    }
}

async function carregarDados(){
    
    const url = "../data/output/summary.json";
    const dados = await capturarJson(url);
    const transacoesSucesso = dados.totalTransacoes - dados.quantidadeErros;
    document.querySelector("#total-transacoes").textContent=dados.totalTransacoes;
    document.querySelector("#valor-aprovado").textContent= dados.valorAprovado.toLocaleString('pt-br', {
                                                                                            style: 'currency',
                                                                                            currency: 'BRL'
    });
    document.querySelector("#total-erros").textContent=dados.quantidadeErros;

    document.querySelector("#system-status").textContent = "Dados carregados com sucesso!";
    document.querySelector("#system-status").style.color = "green";

    const ctx = document.getElementById('myChart');
    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Aprovados', 'Reprovados'],
            datasets: [{
                data: [dados.totalTransacoes, dados.quantidadeErros],
                backgroundColor: ['#2ecc71', '#ff0000ff']
            }]
        }
    });
}