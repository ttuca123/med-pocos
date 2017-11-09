
var tipoFormulario;

function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById(tipoFormulario+'rua').value=("");
            document.getElementById(tipoFormulario+'bairro').value=("");
            document.getElementById(tipoFormulario+'cidade').value=("");
            document.getElementById(tipoFormulario+'uf').value=("");
            
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById(tipoFormulario+'rua').value=(conteudo.logradouro);
            document.getElementById(tipoFormulario+'bairro').value=(conteudo.bairro);
            document.getElementById(tipoFormulario+'cidade').value=(conteudo.localidade);
            document.getElementById(tipoFormulario+'uf').value=(conteudo.uf);
            
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep(tipo, valor) {   	
    	
    	if(tipo==0){
    		tipoFormulario='formNovo:';
    	}else{
    		tipoFormulario='formEditar:';
    	}
    	
        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep) ) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById(tipoFormulario+'rua').value="...";
                document.getElementById(tipoFormulario+'bairro').value="...";
                document.getElementById(tipoFormulario+'cidade').value="...";
                document.getElementById(tipoFormulario+'uf').value="...";
                

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = '//viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } 
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    };