package br.com.aplcorp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aplcorp.dto.CarroListDTO;
import br.com.aplcorp.model.Carro;
import br.com.aplcorp.repository.CarroRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public List<CarroListDTO> listarTodos() {
        List<Carro> carros = carroRepository.findAll();
        return carros.stream()
                     .map(carro -> new CarroListDTO(carro.getId(), carro.getNome(), carro.getPreco(), carro.getAno(), carro.getMarca(), carro.getCidade(), carro.getPlaca(), carro.getDataCad()))
                     .collect(Collectors.toList());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCarro(@RequestBody Carro carro) {
        // Verifica se o carro já existe pela placa
        Optional<Carro> carroExistente = carroRepository.findByPlaca(carro.getPlaca());
        if (carroExistente.isPresent()) {
            // Retorna uma resposta indicando que o carro já existe
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Carro com esta placa já cadastrado.");
        }

        // Salva o novo carro e retorna a resposta adequada
        Carro novoCarro = carroRepository.save(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCarro(@PathVariable long id, @RequestBody Map<String, Object> updates) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isPresent()) {
            Carro carro = carroOptional.get();

            // Atualiza atributos com base no que está presente no JSON
            updates.forEach((atributo, valor) -> {
                switch (atributo) {
                    case "nome":
                        if (valor instanceof String) carro.setNome((String) valor);
                        break;
                    case "preco":
                        if (valor instanceof Number) carro.setPreco(((Number) valor).doubleValue());
                        break;
                    case "ano":
                        if (valor instanceof Number) carro.setAno(((Number) valor).intValue());
                        break;
                    case "marca":
                        if (valor instanceof String) carro.setMarca((String) valor);
                        break;
                    case "cidade":
                        if (valor instanceof String) carro.setCidade((String) valor);
                        break;
                    case "placa":
                        if (valor instanceof String) carro.setPlaca((String) valor);
                        break;
                }
            });

            carroRepository.save(carro);
            return ResponseEntity.ok(carro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/recuperar-detalhes")
    public ResponseEntity<?> recuperarDetalhes(@RequestBody Carro carroRequisicao) {
        Optional<Carro> carro = carroRepository.findByPlaca(carroRequisicao.getPlaca());

        if (carro.isPresent()) {
            // Lógica para recuperação de detalhes do carro aqui
            return ResponseEntity.ok().body(carro.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Carro não encontrado.");
        }
    }
}
