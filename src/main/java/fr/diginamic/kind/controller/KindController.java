package fr.diginamic.kind.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.kind.model.HistoricDTO;
import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.kind.service.KindService;

@CrossOrigin
@RestController()
@RequestMapping("/kinds")
public class KindController {

	@Autowired
	private KindService kindService;

	@GetMapping
	public List<KindDTO> findAll() {
		return this.kindService.findAll();
	}
	
	@GetMapping("/active")
	public List<KindDTO> findActive() {
		return this.kindService.findActive();
	}

	@Transactional
	@PostMapping
	public KindDTO saveKind(@RequestBody KindDTO k) {
		return kindService.save(k);
	}

	@PatchMapping
	public KindDTO update(@RequestBody KindDTO kind) {
		return this.kindService.save(kind);
	}

	@DeleteMapping("/deleteKind/{id}")
	public void deleteKind(@PathVariable long id) {
		this.kindService.deleteKind(id);
	}

	@GetMapping("/historic/{id}/{millis}")
	public KindDTO finKindVersionByIdAndTimestamp(@PathVariable("id") long id, @PathVariable("millis") long millis) {
		return this.kindService.finKindVersionByIdAndTimestamp(id, millis);
	}

	@GetMapping("/historic/{id}")
	public List<HistoricDTO> findKindHistoricById(@PathVariable("id") long id) {
		return this.kindService.findKindHistoricById(id);
	}
}
