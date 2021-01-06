package ir.hamed.socialnetwork.controllers.admin;

import ir.hamed.socialnetwork.mapper.FollowingMapperImpl;
import ir.hamed.socialnetwork.models.admin.dto.FollowingPathReportDto;
import ir.hamed.socialnetwork.models.admin.vm.FollowingPathReportVm;
import ir.hamed.socialnetwork.models.admin.vm.FollowingsReportVm;
import ir.hamed.socialnetwork.payload.response.MessageResponse;
import ir.hamed.socialnetwork.services.admin.FollowingPathReportServiceImpl;
import ir.hamed.socialnetwork.services.admin.FollowingReportServiceMongoImpl;
import ir.hamed.socialnetwork.services.admin.FollowingReportServiceMysqlImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;


@RestController
@RequestMapping("/api/test")
@PreAuthorize("hasRole('ADMIN')")
public class FollowingPathReportController {
    @Value("${mysqldb}")
    private boolean mysqldb;

    @Value("${mongodb}")
    private boolean mongodb;

    @Autowired(required = false)
    FollowingPathReportServiceImpl followingPathReportService;

    FollowingMapperImpl followingMapper = new FollowingMapperImpl();

    @PostMapping("/getfollowingpath")
    public ResponseEntity<?> getfollowingpath(@RequestBody FollowingPathReportVm followingPathReportVm){
        FollowingPathReportDto followingPathReportDto = followingMapper
                .followingPathReportVmToFollowingPathReportDto(followingPathReportVm);
        FollowingPathReportDto resultReportDto = followingPathReportService.getPath(followingPathReportDto);
        FollowingPathReportVm resultReportVm = followingMapper.followingPathReportDtoToFollowingPathReportVm(resultReportDto);
        return ResponseEntity.ok(resultReportVm);
    }
}
