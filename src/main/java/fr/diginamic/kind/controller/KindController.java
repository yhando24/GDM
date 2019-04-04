package fr.diginamic.kind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping
	public KindDTO saveKind(@RequestBody KindDTO k) {
		return kindService.save(k);

	}

	@PatchMapping
	public KindDTO updateKind(@RequestBody KindDTO k) {
		return kindService.save(k);
	}
	
	@DeleteMapping
	public void deleteKind(@RequestBody KindDTO kindDTO) {
		kindService.delete(kindDTO);
	
	}

}
