package Service;

import java.util.List;

public interface GenericService <T> {
    String add(Long  hospitalId, List<T> t);

    void removeById(Long id);

    String updateById(Long id, T t);
}
