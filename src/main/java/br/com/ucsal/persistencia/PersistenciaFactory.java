package br.com.ucsal.persistencia;

import br.com.ucsal.anotacoes.Inject;
import java.lang.reflect.Field;

public class PersistenciaFactory {

    public static void injectDependencies(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        // Verifica todos os campos da classe
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true); // Permite modificar o valor do campo, mesmo que seja privado
                // Cria uma instância do tipo do campo e injeta no objeto
                Object dependency = createDependency(field.getType());
                field.set(object, dependency);
            }
        }
    }

    private static Object createDependency(Class<?> type) {
        try {
            // Aqui podemos adicionar lógica para instanciar os objetos de acordo com o tipo
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar dependência para " + type.getName(), e);
        }
    }
}
