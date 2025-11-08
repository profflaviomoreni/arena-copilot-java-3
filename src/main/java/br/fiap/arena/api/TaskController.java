package br.fiap.arena.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.arena.domain.Task;
import br.fiap.arena.domain.TaskStatus;
import br.fiap.arena.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) { this.service = service; }

    /**
     * Cria uma nova tarefa. Title é obrigatório.
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam("title") String title,
                                    @RequestParam(required = false) Integer priority,
                                    @RequestParam(required = false) TaskStatus status) {
        if (title == null || title.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Title is required");
        }
        Task t = new Task();
        t.setTitle(title);
        t.setPriority(priority == null ? 1 : priority);
        t.setStatus(status == null ? TaskStatus.OPEN : status);
        Task saved = service.create(t);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) TaskStatus status,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        List<Task> data = status == null ? service.listAll() : service.listByStatus(status);
        int from = 0;
        int to = Math.min(size, data.size());
        List<Task> slice = data.subList(from, to);
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("content", slice);
        resp.put("totalElements", data.size());
        resp.put("page", page);
        resp.put("size", size);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteViaGet(@PathVariable("id") Long id) {
        boolean removed = service.delete(id);
        return ResponseEntity.ok(removed);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> stats() {
        return ResponseEntity.ok(service.stats());
    }
}
