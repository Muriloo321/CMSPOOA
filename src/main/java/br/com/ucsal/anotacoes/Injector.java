package br.com.ucsal.anotacoes;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.ucsal.persistencia.ProdutoRepository;

public class Injector {

    private static Map<Class<?>, Object> instances = new HashMap<>();

    public static <T> T getInstance(Class<T> clazz) throws Exception {
        // Verifica se a instância já existe
        if (!instances.containsKey(clazz)) {
            // Cria uma nova instância e injeta as dependências
            T instance = clazz.getDeclaredConstructor().newInstance();
            injectDependencies(instance);
            instances.put(clazz, instance);
        }
        return (T) instances.get(clazz);
    }

    public static void injectDependencies(Object object) throws Exception {
        // Percorre todos os campos da classe para verificar se há injeções
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                // Torna o campo acessível (caso seja privado)
                field.setAccessible(true);

                // Verifica se o campo é do tipo ProdutoRepository e injeta
                if (field.getType() == ProdutoRepository.class) {
                    // Aqui não usamos o 'Impl' específico, injetamos a dependência do tipo ProdutoRepository
                    // O Injector deve ser capaz de entender qual instância concreta usar
                    Object dependency = getInstance(field.getType()); // Chama getInstance para buscar o repositório
                    field.set(object, dependency); // Injeção feita aqui
                } else {
                    // Caso o campo seja de outro tipo, chama getInstance para obter a dependência
                    Object dependency = getInstance(field.getType());
                    field.set(object, dependency);
                }
            }
        }
    }
}
