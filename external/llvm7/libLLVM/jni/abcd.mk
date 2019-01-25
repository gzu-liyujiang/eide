
llvm_lib_aarch64asmparser_DIR=$(LLVM_ROOT_PATH)/lib/Target/AArch64/AsmParser

llvm_lib_aarch64asmparser_SRC_FILES:= \
  $(llvm_lib_aarch64asmparser_DIR)/AArch64AsmParser.cpp



llvm_lib_aarch64asmprinter_DIR=$(LLVM_ROOT_PATH)/lib/Target/AArch64/InstPrinter

llvm_lib_aarch64asmprinter_SRC_FILES:= \
  $(llvm_lib_aarch64asmprinter_DIR)/AArch64InstPrinter.cpp



llvm_lib_aarch64codegen_DIR=$(LLVM_ROOT_PATH)/lib/Target/AArch64

llvm_lib_aarch64codegen_SRC_FILES:= \
  $(llvm_lib_aarch64codegen_DIR)/AArch64A53Fix835769.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64A57FPLoadBalancing.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64AdvSIMDScalarPass.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64AsmPrinter.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64CallLowering.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64CleanupLocalDynamicTLSPass.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64CollectLOH.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64CondBrTuning.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64ConditionOptimizer.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64ConditionalCompares.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64DeadRegisterDefinitionsPass.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64ExpandPseudoInsts.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64FalkorHWPFFix.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64FastISel.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64FrameLowering.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64ISelDAGToDAG.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64ISelLowering.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64InstrInfo.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64InstructionSelector.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64LegalizerInfo.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64LoadStoreOptimizer.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64MCInstLower.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64MacroFusion.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64PBQPRegAlloc.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64PromoteConstant.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64RedundantCopyElimination.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64RegisterBankInfo.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64RegisterInfo.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64SIMDInstrOpt.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64SelectionDAGInfo.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64StorePairSuppress.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64Subtarget.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64TargetMachine.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64TargetObjectFile.cpp \
  $(llvm_lib_aarch64codegen_DIR)/AArch64TargetTransformInfo.cpp



llvm_lib_aarch64desc_DIR=$(LLVM_ROOT_PATH)/lib/Target/AArch64/MCTargetDesc

llvm_lib_aarch64desc_SRC_FILES:= \
  $(llvm_lib_aarch64desc_DIR)/AArch64AsmBackend.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64ELFObjectWriter.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64ELFStreamer.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64MCAsmInfo.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64MCCodeEmitter.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64MCExpr.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64MCTargetDesc.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64MachObjectWriter.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64TargetStreamer.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64WinCOFFObjectWriter.cpp \
  $(llvm_lib_aarch64desc_DIR)/AArch64WinCOFFStreamer.cpp



llvm_lib_aarch64disassembler_DIR=$(LLVM_ROOT_PATH)/lib/Target/AArch64/Disassembler

llvm_lib_aarch64disassembler_SRC_FILES:= \
  $(llvm_lib_aarch64disassembler_DIR)/AArch64Disassembler.cpp \
  $(llvm_lib_aarch64disassembler_DIR)/AArch64ExternalSymbolizer.cpp



llvm_lib_aarch64info_DIR=$(LLVM_ROOT_PATH)/lib/Target/AArch64/TargetInfo

llvm_lib_aarch64info_SRC_FILES:= \
  $(llvm_lib_aarch64info_DIR)/AArch64TargetInfo.cpp



llvm_lib_aarch64utils_DIR=$(LLVM_ROOT_PATH)/lib/Target/AArch64/Utils

llvm_lib_aarch64utils_SRC_FILES:= \
  $(llvm_lib_aarch64utils_DIR)/AArch64BaseInfo.cpp



llvm_lib_analysis_DIR=$(LLVM_ROOT_PATH)/lib/Analysis

llvm_lib_analysis_SRC_FILES:= \
  $(llvm_lib_analysis_DIR)/AliasAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/AliasAnalysisEvaluator.cpp \
  $(llvm_lib_analysis_DIR)/AliasAnalysisSummary.cpp \
  $(llvm_lib_analysis_DIR)/AliasSetTracker.cpp \
  $(llvm_lib_analysis_DIR)/Analysis.cpp \
  $(llvm_lib_analysis_DIR)/AssumptionCache.cpp \
  $(llvm_lib_analysis_DIR)/BasicAliasAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/BlockFrequencyInfo.cpp \
  $(llvm_lib_analysis_DIR)/BlockFrequencyInfoImpl.cpp \
  $(llvm_lib_analysis_DIR)/BranchProbabilityInfo.cpp \
  $(llvm_lib_analysis_DIR)/CFG.cpp \
  $(llvm_lib_analysis_DIR)/CFGPrinter.cpp \
  $(llvm_lib_analysis_DIR)/CFLAndersAliasAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/CFLSteensAliasAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/CGSCCPassManager.cpp \
  $(llvm_lib_analysis_DIR)/CallGraph.cpp \
  $(llvm_lib_analysis_DIR)/CallGraphSCCPass.cpp \
  $(llvm_lib_analysis_DIR)/CallPrinter.cpp \
  $(llvm_lib_analysis_DIR)/CaptureTracking.cpp \
  $(llvm_lib_analysis_DIR)/CmpInstAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/CodeMetrics.cpp \
  $(llvm_lib_analysis_DIR)/ConstantFolding.cpp \
  $(llvm_lib_analysis_DIR)/CostModel.cpp \
  $(llvm_lib_analysis_DIR)/Delinearization.cpp \
  $(llvm_lib_analysis_DIR)/DemandedBits.cpp \
  $(llvm_lib_analysis_DIR)/DependenceAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/DivergenceAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/DomPrinter.cpp \
  $(llvm_lib_analysis_DIR)/DominanceFrontier.cpp \
  $(llvm_lib_analysis_DIR)/EHPersonalities.cpp \
  $(llvm_lib_analysis_DIR)/GlobalsModRef.cpp \
  $(llvm_lib_analysis_DIR)/IVUsers.cpp \
  $(llvm_lib_analysis_DIR)/IndirectCallPromotionAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/InlineCost.cpp \
  $(llvm_lib_analysis_DIR)/InstCount.cpp \
  $(llvm_lib_analysis_DIR)/InstructionSimplify.cpp \
  $(llvm_lib_analysis_DIR)/Interval.cpp \
  $(llvm_lib_analysis_DIR)/IntervalPartition.cpp \
  $(llvm_lib_analysis_DIR)/IteratedDominanceFrontier.cpp \
  $(llvm_lib_analysis_DIR)/LazyBlockFrequencyInfo.cpp \
  $(llvm_lib_analysis_DIR)/LazyBranchProbabilityInfo.cpp \
  $(llvm_lib_analysis_DIR)/LazyCallGraph.cpp \
  $(llvm_lib_analysis_DIR)/LazyValueInfo.cpp \
  $(llvm_lib_analysis_DIR)/Lint.cpp \
  $(llvm_lib_analysis_DIR)/Loads.cpp \
  $(llvm_lib_analysis_DIR)/LoopAccessAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/LoopAnalysisManager.cpp \
  $(llvm_lib_analysis_DIR)/LoopInfo.cpp \
  $(llvm_lib_analysis_DIR)/LoopPass.cpp \
  $(llvm_lib_analysis_DIR)/LoopUnrollAnalyzer.cpp \
  $(llvm_lib_analysis_DIR)/MemDepPrinter.cpp \
  $(llvm_lib_analysis_DIR)/MemDerefPrinter.cpp \
  $(llvm_lib_analysis_DIR)/MemoryBuiltins.cpp \
  $(llvm_lib_analysis_DIR)/MemoryDependenceAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/MemoryLocation.cpp \
  $(llvm_lib_analysis_DIR)/MemorySSA.cpp \
  $(llvm_lib_analysis_DIR)/MemorySSAUpdater.cpp \
  $(llvm_lib_analysis_DIR)/ModuleDebugInfoPrinter.cpp \
  $(llvm_lib_analysis_DIR)/ModuleSummaryAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/MustExecute.cpp \
  $(llvm_lib_analysis_DIR)/ObjCARCAliasAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/ObjCARCAnalysisUtils.cpp \
  $(llvm_lib_analysis_DIR)/ObjCARCInstKind.cpp \
  $(llvm_lib_analysis_DIR)/OptimizationRemarkEmitter.cpp \
  $(llvm_lib_analysis_DIR)/OrderedBasicBlock.cpp \
  $(llvm_lib_analysis_DIR)/PHITransAddr.cpp \
  $(llvm_lib_analysis_DIR)/PhiValues.cpp \
  $(llvm_lib_analysis_DIR)/PostDominators.cpp \
  $(llvm_lib_analysis_DIR)/ProfileSummaryInfo.cpp \
  $(llvm_lib_analysis_DIR)/PtrUseVisitor.cpp \
  $(llvm_lib_analysis_DIR)/RegionInfo.cpp \
  $(llvm_lib_analysis_DIR)/RegionPass.cpp \
  $(llvm_lib_analysis_DIR)/RegionPrinter.cpp \
  $(llvm_lib_analysis_DIR)/ScalarEvolution.cpp \
  $(llvm_lib_analysis_DIR)/ScalarEvolutionAliasAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/ScalarEvolutionExpander.cpp \
  $(llvm_lib_analysis_DIR)/ScalarEvolutionNormalization.cpp \
  $(llvm_lib_analysis_DIR)/ScopedNoAliasAA.cpp \
  $(llvm_lib_analysis_DIR)/SyntheticCountsUtils.cpp \
  $(llvm_lib_analysis_DIR)/TargetLibraryInfo.cpp \
  $(llvm_lib_analysis_DIR)/TargetTransformInfo.cpp \
  $(llvm_lib_analysis_DIR)/Trace.cpp \
  $(llvm_lib_analysis_DIR)/TypeBasedAliasAnalysis.cpp \
  $(llvm_lib_analysis_DIR)/TypeMetadataUtils.cpp \
  $(llvm_lib_analysis_DIR)/ValueLattice.cpp \
  $(llvm_lib_analysis_DIR)/ValueLatticeUtils.cpp \
  $(llvm_lib_analysis_DIR)/ValueTracking.cpp \
  $(llvm_lib_analysis_DIR)/VectorUtils.cpp



llvm_lib_armasmparser_DIR=$(LLVM_ROOT_PATH)/lib/Target/ARM/AsmParser

llvm_lib_armasmparser_SRC_FILES:= \
  $(llvm_lib_armasmparser_DIR)/ARMAsmParser.cpp



llvm_lib_armasmprinter_DIR=$(LLVM_ROOT_PATH)/lib/Target/ARM/InstPrinter

llvm_lib_armasmprinter_SRC_FILES:= \
  $(llvm_lib_armasmprinter_DIR)/ARMInstPrinter.cpp



llvm_lib_armcodegen_DIR=$(LLVM_ROOT_PATH)/lib/Target/ARM

llvm_lib_armcodegen_SRC_FILES:= \
  $(llvm_lib_armcodegen_DIR)/A15SDOptimizer.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMAsmPrinter.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMBaseInstrInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMBaseRegisterInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMCallLowering.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMCodeGenPrepare.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMComputeBlockSize.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMConstantIslandPass.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMConstantPoolValue.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMExpandPseudoInsts.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMFastISel.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMFrameLowering.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMHazardRecognizer.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMISelDAGToDAG.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMISelLowering.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMInstrInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMInstructionSelector.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMLegalizerInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMLoadStoreOptimizer.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMMCInstLower.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMMachineFunctionInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMMacroFusion.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMOptimizeBarriersPass.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMParallelDSP.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMRegisterBankInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMRegisterInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMSelectionDAGInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMSubtarget.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMTargetMachine.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMTargetObjectFile.cpp \
  $(llvm_lib_armcodegen_DIR)/ARMTargetTransformInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/MLxExpansionPass.cpp \
  $(llvm_lib_armcodegen_DIR)/Thumb1FrameLowering.cpp \
  $(llvm_lib_armcodegen_DIR)/Thumb1InstrInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/Thumb2ITBlockPass.cpp \
  $(llvm_lib_armcodegen_DIR)/Thumb2InstrInfo.cpp \
  $(llvm_lib_armcodegen_DIR)/Thumb2SizeReduction.cpp \
  $(llvm_lib_armcodegen_DIR)/ThumbRegisterInfo.cpp



llvm_lib_armdesc_DIR=$(LLVM_ROOT_PATH)/lib/Target/ARM/MCTargetDesc

llvm_lib_armdesc_SRC_FILES:= \
  $(llvm_lib_armdesc_DIR)/ARMAsmBackend.cpp \
  $(llvm_lib_armdesc_DIR)/ARMELFObjectWriter.cpp \
  $(llvm_lib_armdesc_DIR)/ARMELFStreamer.cpp \
  $(llvm_lib_armdesc_DIR)/ARMMCAsmInfo.cpp \
  $(llvm_lib_armdesc_DIR)/ARMMCCodeEmitter.cpp \
  $(llvm_lib_armdesc_DIR)/ARMMCExpr.cpp \
  $(llvm_lib_armdesc_DIR)/ARMMCTargetDesc.cpp \
  $(llvm_lib_armdesc_DIR)/ARMMachORelocationInfo.cpp \
  $(llvm_lib_armdesc_DIR)/ARMMachObjectWriter.cpp \
  $(llvm_lib_armdesc_DIR)/ARMTargetStreamer.cpp \
  $(llvm_lib_armdesc_DIR)/ARMUnwindOpAsm.cpp \
  $(llvm_lib_armdesc_DIR)/ARMWinCOFFObjectWriter.cpp \
  $(llvm_lib_armdesc_DIR)/ARMWinCOFFStreamer.cpp



llvm_lib_armdisassembler_DIR=$(LLVM_ROOT_PATH)/lib/Target/ARM/Disassembler

llvm_lib_armdisassembler_SRC_FILES:= \
  $(llvm_lib_armdisassembler_DIR)/ARMDisassembler.cpp



llvm_lib_arminfo_DIR=$(LLVM_ROOT_PATH)/lib/Target/ARM/TargetInfo

llvm_lib_arminfo_SRC_FILES:= \
  $(llvm_lib_arminfo_DIR)/ARMTargetInfo.cpp



llvm_lib_armutils_DIR=$(LLVM_ROOT_PATH)/lib/Target/ARM/Utils

llvm_lib_armutils_SRC_FILES:= \
  $(llvm_lib_armutils_DIR)/ARMBaseInfo.cpp



llvm_lib_asmparser_DIR=$(LLVM_ROOT_PATH)/lib/AsmParser

llvm_lib_asmparser_SRC_FILES:= \
  $(llvm_lib_asmparser_DIR)/LLLexer.cpp \
  $(llvm_lib_asmparser_DIR)/LLParser.cpp \
  $(llvm_lib_asmparser_DIR)/Parser.cpp



llvm_lib_asmprinter_DIR=$(LLVM_ROOT_PATH)/lib/CodeGen/AsmPrinter

llvm_lib_asmprinter_SRC_FILES:= \
  $(llvm_lib_asmprinter_DIR)/ARMException.cpp \
  $(llvm_lib_asmprinter_DIR)/AccelTable.cpp \
  $(llvm_lib_asmprinter_DIR)/AddressPool.cpp \
  $(llvm_lib_asmprinter_DIR)/AsmPrinter.cpp \
  $(llvm_lib_asmprinter_DIR)/AsmPrinterDwarf.cpp \
  $(llvm_lib_asmprinter_DIR)/AsmPrinterInlineAsm.cpp \
  $(llvm_lib_asmprinter_DIR)/CodeViewDebug.cpp \
  $(llvm_lib_asmprinter_DIR)/DIE.cpp \
  $(llvm_lib_asmprinter_DIR)/DIEHash.cpp \
  $(llvm_lib_asmprinter_DIR)/DbgValueHistoryCalculator.cpp \
  $(llvm_lib_asmprinter_DIR)/DebugHandlerBase.cpp \
  $(llvm_lib_asmprinter_DIR)/DebugLocStream.cpp \
  $(llvm_lib_asmprinter_DIR)/DwarfCFIException.cpp \
  $(llvm_lib_asmprinter_DIR)/DwarfCompileUnit.cpp \
  $(llvm_lib_asmprinter_DIR)/DwarfDebug.cpp \
  $(llvm_lib_asmprinter_DIR)/DwarfExpression.cpp \
  $(llvm_lib_asmprinter_DIR)/DwarfFile.cpp \
  $(llvm_lib_asmprinter_DIR)/DwarfStringPool.cpp \
  $(llvm_lib_asmprinter_DIR)/DwarfUnit.cpp \
  $(llvm_lib_asmprinter_DIR)/EHStreamer.cpp \
  $(llvm_lib_asmprinter_DIR)/ErlangGCPrinter.cpp \
  $(llvm_lib_asmprinter_DIR)/OcamlGCPrinter.cpp \
  $(llvm_lib_asmprinter_DIR)/WinCFGuard.cpp \
  $(llvm_lib_asmprinter_DIR)/WinException.cpp



llvm_lib_binaryformat_DIR=$(LLVM_ROOT_PATH)/lib/BinaryFormat

llvm_lib_binaryformat_SRC_FILES:= \
  $(llvm_lib_binaryformat_DIR)/Dwarf.cpp \
  $(llvm_lib_binaryformat_DIR)/Magic.cpp \
  $(llvm_lib_binaryformat_DIR)/Wasm.cpp



llvm_lib_bitreader_DIR=$(LLVM_ROOT_PATH)/lib/Bitcode/Reader

llvm_lib_bitreader_SRC_FILES:= \
  $(llvm_lib_bitreader_DIR)/BitReader.cpp \
  $(llvm_lib_bitreader_DIR)/BitcodeReader.cpp \
  $(llvm_lib_bitreader_DIR)/BitstreamReader.cpp \
  $(llvm_lib_bitreader_DIR)/MetadataLoader.cpp \
  $(llvm_lib_bitreader_DIR)/ValueList.cpp



llvm_lib_bitwriter_DIR=$(LLVM_ROOT_PATH)/lib/Bitcode/Writer

llvm_lib_bitwriter_SRC_FILES:= \
  $(llvm_lib_bitwriter_DIR)/BitWriter.cpp \
  $(llvm_lib_bitwriter_DIR)/BitcodeWriter.cpp \
  $(llvm_lib_bitwriter_DIR)/BitcodeWriterPass.cpp \
  $(llvm_lib_bitwriter_DIR)/ValueEnumerator.cpp



llvm_lib_codegen_DIR=$(LLVM_ROOT_PATH)/lib/CodeGen

llvm_lib_codegen_SRC_FILES:= \
  $(llvm_lib_codegen_DIR)/AggressiveAntiDepBreaker.cpp \
  $(llvm_lib_codegen_DIR)/AllocationOrder.cpp \
  $(llvm_lib_codegen_DIR)/Analysis.cpp \
  $(llvm_lib_codegen_DIR)/AtomicExpandPass.cpp \
  $(llvm_lib_codegen_DIR)/BasicTargetTransformInfo.cpp \
  $(llvm_lib_codegen_DIR)/BranchFolding.cpp \
  $(llvm_lib_codegen_DIR)/BranchRelaxation.cpp \
  $(llvm_lib_codegen_DIR)/BreakFalseDeps.cpp \
  $(llvm_lib_codegen_DIR)/BuiltinGCs.cpp \
  $(llvm_lib_codegen_DIR)/CFIInstrInserter.cpp \
  $(llvm_lib_codegen_DIR)/CalcSpillWeights.cpp \
  $(llvm_lib_codegen_DIR)/CallingConvLower.cpp \
  $(llvm_lib_codegen_DIR)/CodeGen.cpp \
  $(llvm_lib_codegen_DIR)/CodeGenPrepare.cpp \
  $(llvm_lib_codegen_DIR)/CriticalAntiDepBreaker.cpp \
  $(llvm_lib_codegen_DIR)/DFAPacketizer.cpp \
  $(llvm_lib_codegen_DIR)/DeadMachineInstructionElim.cpp \
  $(llvm_lib_codegen_DIR)/DetectDeadLanes.cpp \
  $(llvm_lib_codegen_DIR)/DwarfEHPrepare.cpp \
  $(llvm_lib_codegen_DIR)/EarlyIfConversion.cpp \
  $(llvm_lib_codegen_DIR)/EdgeBundles.cpp \
  $(llvm_lib_codegen_DIR)/ExecutionDomainFix.cpp \
  $(llvm_lib_codegen_DIR)/ExpandISelPseudos.cpp \
  $(llvm_lib_codegen_DIR)/ExpandMemCmp.cpp \
  $(llvm_lib_codegen_DIR)/ExpandPostRAPseudos.cpp \
  $(llvm_lib_codegen_DIR)/ExpandReductions.cpp \
  $(llvm_lib_codegen_DIR)/FEntryInserter.cpp \
  $(llvm_lib_codegen_DIR)/FaultMaps.cpp \
  $(llvm_lib_codegen_DIR)/FuncletLayout.cpp \
  $(llvm_lib_codegen_DIR)/GCMetadata.cpp \
  $(llvm_lib_codegen_DIR)/GCMetadataPrinter.cpp \
  $(llvm_lib_codegen_DIR)/GCRootLowering.cpp \
  $(llvm_lib_codegen_DIR)/GCStrategy.cpp \
  $(llvm_lib_codegen_DIR)/GlobalMerge.cpp \
  $(llvm_lib_codegen_DIR)/IfConversion.cpp \
  $(llvm_lib_codegen_DIR)/ImplicitNullChecks.cpp \
  $(llvm_lib_codegen_DIR)/IndirectBrExpandPass.cpp \
  $(llvm_lib_codegen_DIR)/InlineSpiller.cpp \
  $(llvm_lib_codegen_DIR)/InterferenceCache.cpp \
  $(llvm_lib_codegen_DIR)/InterleavedAccessPass.cpp \
  $(llvm_lib_codegen_DIR)/IntrinsicLowering.cpp \
  $(llvm_lib_codegen_DIR)/LLVMTargetMachine.cpp \
  $(llvm_lib_codegen_DIR)/LatencyPriorityQueue.cpp \
  $(llvm_lib_codegen_DIR)/LazyMachineBlockFrequencyInfo.cpp \
  $(llvm_lib_codegen_DIR)/LexicalScopes.cpp \
  $(llvm_lib_codegen_DIR)/LiveDebugValues.cpp \
  $(llvm_lib_codegen_DIR)/LiveDebugVariables.cpp \
  $(llvm_lib_codegen_DIR)/LiveInterval.cpp \
  $(llvm_lib_codegen_DIR)/LiveIntervalUnion.cpp \
  $(llvm_lib_codegen_DIR)/LiveIntervals.cpp \
  $(llvm_lib_codegen_DIR)/LivePhysRegs.cpp \
  $(llvm_lib_codegen_DIR)/LiveRangeCalc.cpp \
  $(llvm_lib_codegen_DIR)/LiveRangeEdit.cpp \
  $(llvm_lib_codegen_DIR)/LiveRangeShrink.cpp \
  $(llvm_lib_codegen_DIR)/LiveRegMatrix.cpp \
  $(llvm_lib_codegen_DIR)/LiveRegUnits.cpp \
  $(llvm_lib_codegen_DIR)/LiveStacks.cpp \
  $(llvm_lib_codegen_DIR)/LiveVariables.cpp \
  $(llvm_lib_codegen_DIR)/LocalStackSlotAllocation.cpp \
  $(llvm_lib_codegen_DIR)/LoopTraversal.cpp \
  $(llvm_lib_codegen_DIR)/LowLevelType.cpp \
  $(llvm_lib_codegen_DIR)/LowerEmuTLS.cpp \
  $(llvm_lib_codegen_DIR)/MIRCanonicalizerPass.cpp \
  $(llvm_lib_codegen_DIR)/MIRPrinter.cpp \
  $(llvm_lib_codegen_DIR)/MIRPrintingPass.cpp \
  $(llvm_lib_codegen_DIR)/MachineBasicBlock.cpp \
  $(llvm_lib_codegen_DIR)/MachineBlockFrequencyInfo.cpp \
  $(llvm_lib_codegen_DIR)/MachineBlockPlacement.cpp \
  $(llvm_lib_codegen_DIR)/MachineBranchProbabilityInfo.cpp \
  $(llvm_lib_codegen_DIR)/MachineCSE.cpp \
  $(llvm_lib_codegen_DIR)/MachineCombiner.cpp \
  $(llvm_lib_codegen_DIR)/MachineCopyPropagation.cpp \
  $(llvm_lib_codegen_DIR)/MachineDominanceFrontier.cpp \
  $(llvm_lib_codegen_DIR)/MachineDominators.cpp \
  $(llvm_lib_codegen_DIR)/MachineFrameInfo.cpp \
  $(llvm_lib_codegen_DIR)/MachineFunction.cpp \
  $(llvm_lib_codegen_DIR)/MachineFunctionPass.cpp \
  $(llvm_lib_codegen_DIR)/MachineFunctionPrinterPass.cpp \
  $(llvm_lib_codegen_DIR)/MachineInstr.cpp \
  $(llvm_lib_codegen_DIR)/MachineInstrBundle.cpp \
  $(llvm_lib_codegen_DIR)/MachineLICM.cpp \
  $(llvm_lib_codegen_DIR)/MachineLoopInfo.cpp \
  $(llvm_lib_codegen_DIR)/MachineModuleInfo.cpp \
  $(llvm_lib_codegen_DIR)/MachineModuleInfoImpls.cpp \
  $(llvm_lib_codegen_DIR)/MachineOperand.cpp \
  $(llvm_lib_codegen_DIR)/MachineOptimizationRemarkEmitter.cpp \
  $(llvm_lib_codegen_DIR)/MachineOutliner.cpp \
  $(llvm_lib_codegen_DIR)/MachinePassRegistry.cpp \
  $(llvm_lib_codegen_DIR)/MachinePipeliner.cpp \
  $(llvm_lib_codegen_DIR)/MachinePostDominators.cpp \
  $(llvm_lib_codegen_DIR)/MachineRegionInfo.cpp \
  $(llvm_lib_codegen_DIR)/MachineRegisterInfo.cpp \
  $(llvm_lib_codegen_DIR)/MachineSSAUpdater.cpp \
  $(llvm_lib_codegen_DIR)/MachineScheduler.cpp \
  $(llvm_lib_codegen_DIR)/MachineSink.cpp \
  $(llvm_lib_codegen_DIR)/MachineTraceMetrics.cpp \
  $(llvm_lib_codegen_DIR)/MachineVerifier.cpp \
  $(llvm_lib_codegen_DIR)/MacroFusion.cpp \
  $(llvm_lib_codegen_DIR)/OptimizePHIs.cpp \
  $(llvm_lib_codegen_DIR)/PHIElimination.cpp \
  $(llvm_lib_codegen_DIR)/PHIEliminationUtils.cpp \
  $(llvm_lib_codegen_DIR)/ParallelCG.cpp \
  $(llvm_lib_codegen_DIR)/PatchableFunction.cpp \
  $(llvm_lib_codegen_DIR)/PeepholeOptimizer.cpp \
  $(llvm_lib_codegen_DIR)/PostRAHazardRecognizer.cpp \
  $(llvm_lib_codegen_DIR)/PostRASchedulerList.cpp \
  $(llvm_lib_codegen_DIR)/PreISelIntrinsicLowering.cpp \
  $(llvm_lib_codegen_DIR)/ProcessImplicitDefs.cpp \
  $(llvm_lib_codegen_DIR)/PrologEpilogInserter.cpp \
  $(llvm_lib_codegen_DIR)/PseudoSourceValue.cpp \
  $(llvm_lib_codegen_DIR)/ReachingDefAnalysis.cpp \
  $(llvm_lib_codegen_DIR)/RegAllocBase.cpp \
  $(llvm_lib_codegen_DIR)/RegAllocBasic.cpp \
  $(llvm_lib_codegen_DIR)/RegAllocFast.cpp \
  $(llvm_lib_codegen_DIR)/RegAllocGreedy.cpp \
  $(llvm_lib_codegen_DIR)/RegAllocPBQP.cpp \
  $(llvm_lib_codegen_DIR)/RegUsageInfoCollector.cpp \
  $(llvm_lib_codegen_DIR)/RegUsageInfoPropagate.cpp \
  $(llvm_lib_codegen_DIR)/RegisterClassInfo.cpp \
  $(llvm_lib_codegen_DIR)/RegisterCoalescer.cpp \
  $(llvm_lib_codegen_DIR)/RegisterPressure.cpp \
  $(llvm_lib_codegen_DIR)/RegisterScavenging.cpp \
  $(llvm_lib_codegen_DIR)/RegisterUsageInfo.cpp \
  $(llvm_lib_codegen_DIR)/RenameIndependentSubregs.cpp \
  $(llvm_lib_codegen_DIR)/ResetMachineFunctionPass.cpp \
  $(llvm_lib_codegen_DIR)/SafeStack.cpp \
  $(llvm_lib_codegen_DIR)/SafeStackColoring.cpp \
  $(llvm_lib_codegen_DIR)/SafeStackLayout.cpp \
  $(llvm_lib_codegen_DIR)/ScalarizeMaskedMemIntrin.cpp \
  $(llvm_lib_codegen_DIR)/ScheduleDAG.cpp \
  $(llvm_lib_codegen_DIR)/ScheduleDAGInstrs.cpp \
  $(llvm_lib_codegen_DIR)/ScheduleDAGPrinter.cpp \
  $(llvm_lib_codegen_DIR)/ScoreboardHazardRecognizer.cpp \
  $(llvm_lib_codegen_DIR)/ShadowStackGCLowering.cpp \
  $(llvm_lib_codegen_DIR)/ShrinkWrap.cpp \
  $(llvm_lib_codegen_DIR)/SjLjEHPrepare.cpp \
  $(llvm_lib_codegen_DIR)/SlotIndexes.cpp \
  $(llvm_lib_codegen_DIR)/SpillPlacement.cpp \
  $(llvm_lib_codegen_DIR)/SplitKit.cpp \
  $(llvm_lib_codegen_DIR)/StackColoring.cpp \
  $(llvm_lib_codegen_DIR)/StackMapLivenessAnalysis.cpp \
  $(llvm_lib_codegen_DIR)/StackMaps.cpp \
  $(llvm_lib_codegen_DIR)/StackProtector.cpp \
  $(llvm_lib_codegen_DIR)/StackSlotColoring.cpp \
  $(llvm_lib_codegen_DIR)/TailDuplication.cpp \
  $(llvm_lib_codegen_DIR)/TailDuplicator.cpp \
  $(llvm_lib_codegen_DIR)/TargetFrameLoweringImpl.cpp \
  $(llvm_lib_codegen_DIR)/TargetInstrInfo.cpp \
  $(llvm_lib_codegen_DIR)/TargetLoweringBase.cpp \
  $(llvm_lib_codegen_DIR)/TargetLoweringObjectFileImpl.cpp \
  $(llvm_lib_codegen_DIR)/TargetOptionsImpl.cpp \
  $(llvm_lib_codegen_DIR)/TargetPassConfig.cpp \
  $(llvm_lib_codegen_DIR)/TargetRegisterInfo.cpp \
  $(llvm_lib_codegen_DIR)/TargetSchedule.cpp \
  $(llvm_lib_codegen_DIR)/TargetSubtargetInfo.cpp \
  $(llvm_lib_codegen_DIR)/TwoAddressInstructionPass.cpp \
  $(llvm_lib_codegen_DIR)/UnreachableBlockElim.cpp \
  $(llvm_lib_codegen_DIR)/ValueTypes.cpp \
  $(llvm_lib_codegen_DIR)/VirtRegMap.cpp \
  $(llvm_lib_codegen_DIR)/WasmEHPrepare.cpp \
  $(llvm_lib_codegen_DIR)/WinEHPrepare.cpp \
  $(llvm_lib_codegen_DIR)/XRayInstrumentation.cpp



llvm_lib_core_DIR=$(LLVM_ROOT_PATH)/lib/IR

llvm_lib_core_SRC_FILES:= \
  $(llvm_lib_core_DIR)/AsmWriter.cpp \
  $(llvm_lib_core_DIR)/Attributes.cpp \
  $(llvm_lib_core_DIR)/AutoUpgrade.cpp \
  $(llvm_lib_core_DIR)/BasicBlock.cpp \
  $(llvm_lib_core_DIR)/Comdat.cpp \
  $(llvm_lib_core_DIR)/ConstantFold.cpp \
  $(llvm_lib_core_DIR)/ConstantRange.cpp \
  $(llvm_lib_core_DIR)/Constants.cpp \
  $(llvm_lib_core_DIR)/Core.cpp \
  $(llvm_lib_core_DIR)/DIBuilder.cpp \
  $(llvm_lib_core_DIR)/DataLayout.cpp \
  $(llvm_lib_core_DIR)/DebugInfo.cpp \
  $(llvm_lib_core_DIR)/DebugInfoMetadata.cpp \
  $(llvm_lib_core_DIR)/DebugLoc.cpp \
  $(llvm_lib_core_DIR)/DiagnosticHandler.cpp \
  $(llvm_lib_core_DIR)/DiagnosticInfo.cpp \
  $(llvm_lib_core_DIR)/DiagnosticPrinter.cpp \
  $(llvm_lib_core_DIR)/DomTreeUpdater.cpp \
  $(llvm_lib_core_DIR)/Dominators.cpp \
  $(llvm_lib_core_DIR)/Function.cpp \
  $(llvm_lib_core_DIR)/GVMaterializer.cpp \
  $(llvm_lib_core_DIR)/Globals.cpp \
  $(llvm_lib_core_DIR)/IRBuilder.cpp \
  $(llvm_lib_core_DIR)/IRPrintingPasses.cpp \
  $(llvm_lib_core_DIR)/InlineAsm.cpp \
  $(llvm_lib_core_DIR)/Instruction.cpp \
  $(llvm_lib_core_DIR)/Instructions.cpp \
  $(llvm_lib_core_DIR)/IntrinsicInst.cpp \
  $(llvm_lib_core_DIR)/LLVMContext.cpp \
  $(llvm_lib_core_DIR)/LLVMContextImpl.cpp \
  $(llvm_lib_core_DIR)/LegacyPassManager.cpp \
  $(llvm_lib_core_DIR)/MDBuilder.cpp \
  $(llvm_lib_core_DIR)/Mangler.cpp \
  $(llvm_lib_core_DIR)/Metadata.cpp \
  $(llvm_lib_core_DIR)/Module.cpp \
  $(llvm_lib_core_DIR)/ModuleSummaryIndex.cpp \
  $(llvm_lib_core_DIR)/Operator.cpp \
  $(llvm_lib_core_DIR)/OptBisect.cpp \
  $(llvm_lib_core_DIR)/Pass.cpp \
  $(llvm_lib_core_DIR)/PassManager.cpp \
  $(llvm_lib_core_DIR)/PassRegistry.cpp \
  $(llvm_lib_core_DIR)/ProfileSummary.cpp \
  $(llvm_lib_core_DIR)/SafepointIRVerifier.cpp \
  $(llvm_lib_core_DIR)/Statepoint.cpp \
  $(llvm_lib_core_DIR)/Type.cpp \
  $(llvm_lib_core_DIR)/TypeFinder.cpp \
  $(llvm_lib_core_DIR)/Use.cpp \
  $(llvm_lib_core_DIR)/User.cpp \
  $(llvm_lib_core_DIR)/Value.cpp \
  $(llvm_lib_core_DIR)/ValueSymbolTable.cpp \
  $(llvm_lib_core_DIR)/Verifier.cpp



llvm_lib_coroutines_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/Coroutines

llvm_lib_coroutines_SRC_FILES:= \
  $(llvm_lib_coroutines_DIR)/CoroCleanup.cpp \
  $(llvm_lib_coroutines_DIR)/CoroEarly.cpp \
  $(llvm_lib_coroutines_DIR)/CoroElide.cpp \
  $(llvm_lib_coroutines_DIR)/CoroFrame.cpp \
  $(llvm_lib_coroutines_DIR)/CoroSplit.cpp \
  $(llvm_lib_coroutines_DIR)/Coroutines.cpp



llvm_lib_coverage_DIR=$(LLVM_ROOT_PATH)/lib/ProfileData/Coverage

llvm_lib_coverage_SRC_FILES:= \
  $(llvm_lib_coverage_DIR)/CoverageMapping.cpp \
  $(llvm_lib_coverage_DIR)/CoverageMappingReader.cpp \
  $(llvm_lib_coverage_DIR)/CoverageMappingWriter.cpp



llvm_lib_debuginfocodeview_DIR=$(LLVM_ROOT_PATH)/lib/DebugInfo/CodeView

llvm_lib_debuginfocodeview_SRC_FILES:= \
  $(llvm_lib_debuginfocodeview_DIR)/AppendingTypeTableBuilder.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/CVSymbolVisitor.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/CVTypeVisitor.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/CodeViewError.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/CodeViewRecordIO.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/ContinuationRecordBuilder.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugChecksumsSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugCrossExSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugCrossImpSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugFrameDataSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugInlineeLinesSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugLinesSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugStringTableSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugSubsectionRecord.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugSubsectionVisitor.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugSymbolRVASubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/DebugSymbolsSubsection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/EnumTables.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/Formatters.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/GlobalTypeTableBuilder.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/LazyRandomTypeCollection.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/Line.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/MergingTypeTableBuilder.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/RecordName.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/RecordSerialization.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/SimpleTypeSerializer.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/StringsAndChecksums.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/SymbolDumper.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/SymbolRecordMapping.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/SymbolSerializer.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/TypeDumpVisitor.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/TypeHashing.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/TypeIndex.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/TypeIndexDiscovery.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/TypeRecordMapping.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/TypeStreamMerger.cpp \
  $(llvm_lib_debuginfocodeview_DIR)/TypeTableCollection.cpp



llvm_lib_debuginfodwarf_DIR=$(LLVM_ROOT_PATH)/lib/DebugInfo/DWARF

llvm_lib_debuginfodwarf_SRC_FILES:= \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFAbbreviationDeclaration.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFAcceleratorTable.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFAddressRange.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFCompileUnit.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFContext.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDataExtractor.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugAbbrev.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugAddr.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugArangeSet.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugAranges.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugFrame.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugInfoEntry.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugLine.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugLoc.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugMacro.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugPubTable.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugRangeList.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDebugRnglists.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFDie.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFExpression.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFFormValue.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFGdbIndex.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFListTable.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFTypeUnit.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFUnit.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFUnitIndex.cpp \
  $(llvm_lib_debuginfodwarf_DIR)/DWARFVerifier.cpp



llvm_lib_debuginfomsf_DIR=$(LLVM_ROOT_PATH)/lib/DebugInfo/MSF

llvm_lib_debuginfomsf_SRC_FILES:= \
  $(llvm_lib_debuginfomsf_DIR)/MSFBuilder.cpp \
  $(llvm_lib_debuginfomsf_DIR)/MSFCommon.cpp \
  $(llvm_lib_debuginfomsf_DIR)/MSFError.cpp \
  $(llvm_lib_debuginfomsf_DIR)/MappedBlockStream.cpp



llvm_lib_debuginfopdb_DIR=$(LLVM_ROOT_PATH)/lib/DebugInfo/PDB

llvm_lib_debuginfopdb_SRC_FILES:= \
  $(llvm_lib_debuginfopdb_DIR)/GenericError.cpp \
  $(llvm_lib_debuginfopdb_DIR)/IPDBSourceFile.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDB.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBContext.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBExtras.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBInterfaceAnchors.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymDumper.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbol.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolAnnotation.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolBlock.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolCompiland.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolCompilandDetails.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolCompilandEnv.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolCustom.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolData.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolExe.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolFunc.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolFuncDebugEnd.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolFuncDebugStart.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolLabel.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolPublicSymbol.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolThunk.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeArray.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeBaseClass.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeBuiltin.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeCustom.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeDimension.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeEnum.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeFriend.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeFunctionArg.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeFunctionSig.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeManaged.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypePointer.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeTypedef.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeUDT.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeVTable.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolTypeVTableShape.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolUnknown.cpp \
  $(llvm_lib_debuginfopdb_DIR)/PDBSymbolUsingNamespace.cpp \
  $(llvm_lib_debuginfopdb_DIR)/UDTLayout.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/PDBStringTable.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/TpiStreamBuilder.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/RawError.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/GlobalsStream.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/PDBFileBuilder.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/DbiModuleList.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/EnumTables.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/ModuleDebugStream.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeBuiltinSymbol.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeEnumModules.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NamedStreamMap.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/InfoStream.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/HashTable.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeEnumSymbol.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeSession.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/DbiStream.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/TpiHashing.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/GSIStreamBuilder.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/SymbolStream.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/PDBStringTableBuilder.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/PublicsStream.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeExeSymbol.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeRawSymbol.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeEnumTypes.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/NativeCompilandSymbol.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/PDBFile.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/Hash.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/DbiStreamBuilder.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/DbiModuleDescriptor.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/InfoStreamBuilder.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/DbiModuleDescriptorBuilder.cpp \
  $(llvm_lib_debuginfopdb_DIR)/Native/TpiStream.cpp



llvm_lib_demangle_DIR=$(LLVM_ROOT_PATH)/lib/Demangle

llvm_lib_demangle_SRC_FILES:= \
  $(llvm_lib_demangle_DIR)/ItaniumDemangle.cpp \
  $(llvm_lib_demangle_DIR)/MicrosoftDemangle.cpp



llvm_lib_dlltooldriver_DIR=$(LLVM_ROOT_PATH)/lib/ToolDrivers/llvm-dlltool

llvm_lib_dlltooldriver_SRC_FILES:= \
  $(llvm_lib_dlltooldriver_DIR)/DlltoolDriver.cpp



llvm_lib_globalisel_DIR=$(LLVM_ROOT_PATH)/lib/CodeGen/GlobalISel

llvm_lib_globalisel_SRC_FILES:= \
  $(llvm_lib_globalisel_DIR)/CallLowering.cpp \
  $(llvm_lib_globalisel_DIR)/Combiner.cpp \
  $(llvm_lib_globalisel_DIR)/CombinerHelper.cpp \
  $(llvm_lib_globalisel_DIR)/GlobalISel.cpp \
  $(llvm_lib_globalisel_DIR)/IRTranslator.cpp \
  $(llvm_lib_globalisel_DIR)/InstructionSelect.cpp \
  $(llvm_lib_globalisel_DIR)/InstructionSelector.cpp \
  $(llvm_lib_globalisel_DIR)/LegalityPredicates.cpp \
  $(llvm_lib_globalisel_DIR)/LegalizeMutations.cpp \
  $(llvm_lib_globalisel_DIR)/Legalizer.cpp \
  $(llvm_lib_globalisel_DIR)/LegalizerHelper.cpp \
  $(llvm_lib_globalisel_DIR)/LegalizerInfo.cpp \
  $(llvm_lib_globalisel_DIR)/Localizer.cpp \
  $(llvm_lib_globalisel_DIR)/MachineIRBuilder.cpp \
  $(llvm_lib_globalisel_DIR)/RegBankSelect.cpp \
  $(llvm_lib_globalisel_DIR)/RegisterBank.cpp \
  $(llvm_lib_globalisel_DIR)/RegisterBankInfo.cpp \
  $(llvm_lib_globalisel_DIR)/Utils.cpp



llvm_lib_instcombine_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/InstCombine

llvm_lib_instcombine_SRC_FILES:= \
  $(llvm_lib_instcombine_DIR)/InstCombineAddSub.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineAndOrXor.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineCalls.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineCasts.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineCompares.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineLoadStoreAlloca.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineMulDivRem.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombinePHI.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineSelect.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineShifts.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineSimplifyDemanded.cpp \
  $(llvm_lib_instcombine_DIR)/InstCombineVectorOps.cpp \
  $(llvm_lib_instcombine_DIR)/InstructionCombining.cpp



llvm_lib_instrumentation_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/Instrumentation

llvm_lib_instrumentation_SRC_FILES:= \
  $(llvm_lib_instrumentation_DIR)/AddressSanitizer.cpp \
  $(llvm_lib_instrumentation_DIR)/BoundsChecking.cpp \
  $(llvm_lib_instrumentation_DIR)/CGProfile.cpp \
  $(llvm_lib_instrumentation_DIR)/DataFlowSanitizer.cpp \
  $(llvm_lib_instrumentation_DIR)/EfficiencySanitizer.cpp \
  $(llvm_lib_instrumentation_DIR)/GCOVProfiling.cpp \
  $(llvm_lib_instrumentation_DIR)/HWAddressSanitizer.cpp \
  $(llvm_lib_instrumentation_DIR)/IndirectCallPromotion.cpp \
  $(llvm_lib_instrumentation_DIR)/InstrProfiling.cpp \
  $(llvm_lib_instrumentation_DIR)/Instrumentation.cpp \
  $(llvm_lib_instrumentation_DIR)/MemorySanitizer.cpp \
  $(llvm_lib_instrumentation_DIR)/PGOInstrumentation.cpp \
  $(llvm_lib_instrumentation_DIR)/PGOMemOPSizeOpt.cpp \
  $(llvm_lib_instrumentation_DIR)/SanitizerCoverage.cpp \
  $(llvm_lib_instrumentation_DIR)/ThreadSanitizer.cpp



llvm_lib_ipo_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/IPO

llvm_lib_ipo_SRC_FILES:= \
  $(llvm_lib_ipo_DIR)/AlwaysInliner.cpp \
  $(llvm_lib_ipo_DIR)/ArgumentPromotion.cpp \
  $(llvm_lib_ipo_DIR)/BarrierNoopPass.cpp \
  $(llvm_lib_ipo_DIR)/BlockExtractor.cpp \
  $(llvm_lib_ipo_DIR)/CalledValuePropagation.cpp \
  $(llvm_lib_ipo_DIR)/ConstantMerge.cpp \
  $(llvm_lib_ipo_DIR)/CrossDSOCFI.cpp \
  $(llvm_lib_ipo_DIR)/DeadArgumentElimination.cpp \
  $(llvm_lib_ipo_DIR)/ElimAvailExtern.cpp \
  $(llvm_lib_ipo_DIR)/ExtractGV.cpp \
  $(llvm_lib_ipo_DIR)/ForceFunctionAttrs.cpp \
  $(llvm_lib_ipo_DIR)/FunctionAttrs.cpp \
  $(llvm_lib_ipo_DIR)/FunctionImport.cpp \
  $(llvm_lib_ipo_DIR)/GlobalDCE.cpp \
  $(llvm_lib_ipo_DIR)/GlobalOpt.cpp \
  $(llvm_lib_ipo_DIR)/GlobalSplit.cpp \
  $(llvm_lib_ipo_DIR)/IPConstantPropagation.cpp \
  $(llvm_lib_ipo_DIR)/IPO.cpp \
  $(llvm_lib_ipo_DIR)/InferFunctionAttrs.cpp \
  $(llvm_lib_ipo_DIR)/InlineSimple.cpp \
  $(llvm_lib_ipo_DIR)/Inliner.cpp \
  $(llvm_lib_ipo_DIR)/Internalize.cpp \
  $(llvm_lib_ipo_DIR)/LoopExtractor.cpp \
  $(llvm_lib_ipo_DIR)/LowerTypeTests.cpp \
  $(llvm_lib_ipo_DIR)/MergeFunctions.cpp \
  $(llvm_lib_ipo_DIR)/PartialInlining.cpp \
  $(llvm_lib_ipo_DIR)/PassManagerBuilder.cpp \
  $(llvm_lib_ipo_DIR)/PruneEH.cpp \
  $(llvm_lib_ipo_DIR)/SCCP.cpp \
  $(llvm_lib_ipo_DIR)/SampleProfile.cpp \
  $(llvm_lib_ipo_DIR)/StripDeadPrototypes.cpp \
  $(llvm_lib_ipo_DIR)/StripSymbols.cpp \
  $(llvm_lib_ipo_DIR)/SyntheticCountsPropagation.cpp \
  $(llvm_lib_ipo_DIR)/ThinLTOBitcodeWriter.cpp \
  $(llvm_lib_ipo_DIR)/WholeProgramDevirt.cpp



llvm_lib_irreader_DIR=$(LLVM_ROOT_PATH)/lib/IRReader

llvm_lib_irreader_SRC_FILES:= \
  $(llvm_lib_irreader_DIR)/IRReader.cpp



llvm_lib_libdriver_DIR=$(LLVM_ROOT_PATH)/lib/ToolDrivers/llvm-lib

llvm_lib_libdriver_SRC_FILES:= \
  $(llvm_lib_libdriver_DIR)/LibDriver.cpp



llvm_lib_linker_DIR=$(LLVM_ROOT_PATH)/lib/Linker

llvm_lib_linker_SRC_FILES:= \
  $(llvm_lib_linker_DIR)/IRMover.cpp \
  $(llvm_lib_linker_DIR)/LinkModules.cpp



llvm_lib_lto_DIR=$(LLVM_ROOT_PATH)/lib/LTO

llvm_lib_lto_SRC_FILES:= \
  $(llvm_lib_lto_DIR)/Caching.cpp \
  $(llvm_lib_lto_DIR)/LTO.cpp \
  $(llvm_lib_lto_DIR)/LTOBackend.cpp \
  $(llvm_lib_lto_DIR)/LTOCodeGenerator.cpp \
  $(llvm_lib_lto_DIR)/LTOModule.cpp \
  $(llvm_lib_lto_DIR)/ThinLTOCodeGenerator.cpp \
  $(llvm_lib_lto_DIR)/UpdateCompilerUsed.cpp



llvm_lib_mc_DIR=$(LLVM_ROOT_PATH)/lib/MC

llvm_lib_mc_SRC_FILES:= \
  $(llvm_lib_mc_DIR)/ConstantPools.cpp \
  $(llvm_lib_mc_DIR)/ELFObjectWriter.cpp \
  $(llvm_lib_mc_DIR)/MCAsmBackend.cpp \
  $(llvm_lib_mc_DIR)/MCAsmInfo.cpp \
  $(llvm_lib_mc_DIR)/MCAsmInfoCOFF.cpp \
  $(llvm_lib_mc_DIR)/MCAsmInfoDarwin.cpp \
  $(llvm_lib_mc_DIR)/MCAsmInfoELF.cpp \
  $(llvm_lib_mc_DIR)/MCAsmInfoWasm.cpp \
  $(llvm_lib_mc_DIR)/MCAsmMacro.cpp \
  $(llvm_lib_mc_DIR)/MCAsmStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCAssembler.cpp \
  $(llvm_lib_mc_DIR)/MCCodeEmitter.cpp \
  $(llvm_lib_mc_DIR)/MCCodePadder.cpp \
  $(llvm_lib_mc_DIR)/MCCodeView.cpp \
  $(llvm_lib_mc_DIR)/MCContext.cpp \
  $(llvm_lib_mc_DIR)/MCDwarf.cpp \
  $(llvm_lib_mc_DIR)/MCELFObjectTargetWriter.cpp \
  $(llvm_lib_mc_DIR)/MCELFStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCExpr.cpp \
  $(llvm_lib_mc_DIR)/MCFragment.cpp \
  $(llvm_lib_mc_DIR)/MCInst.cpp \
  $(llvm_lib_mc_DIR)/MCInstPrinter.cpp \
  $(llvm_lib_mc_DIR)/MCInstrAnalysis.cpp \
  $(llvm_lib_mc_DIR)/MCInstrDesc.cpp \
  $(llvm_lib_mc_DIR)/MCLabel.cpp \
  $(llvm_lib_mc_DIR)/MCLinkerOptimizationHint.cpp \
  $(llvm_lib_mc_DIR)/MCMachOStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCMachObjectTargetWriter.cpp \
  $(llvm_lib_mc_DIR)/MCNullStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCObjectFileInfo.cpp \
  $(llvm_lib_mc_DIR)/MCObjectStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCObjectWriter.cpp \
  $(llvm_lib_mc_DIR)/MCRegisterInfo.cpp \
  $(llvm_lib_mc_DIR)/MCSchedule.cpp \
  $(llvm_lib_mc_DIR)/MCSection.cpp \
  $(llvm_lib_mc_DIR)/MCSectionCOFF.cpp \
  $(llvm_lib_mc_DIR)/MCSectionELF.cpp \
  $(llvm_lib_mc_DIR)/MCSectionMachO.cpp \
  $(llvm_lib_mc_DIR)/MCSectionWasm.cpp \
  $(llvm_lib_mc_DIR)/MCStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCSubtargetInfo.cpp \
  $(llvm_lib_mc_DIR)/MCSymbol.cpp \
  $(llvm_lib_mc_DIR)/MCSymbolELF.cpp \
  $(llvm_lib_mc_DIR)/MCTargetOptions.cpp \
  $(llvm_lib_mc_DIR)/MCValue.cpp \
  $(llvm_lib_mc_DIR)/MCWasmObjectTargetWriter.cpp \
  $(llvm_lib_mc_DIR)/MCWasmStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCWin64EH.cpp \
  $(llvm_lib_mc_DIR)/MCWinCOFFStreamer.cpp \
  $(llvm_lib_mc_DIR)/MCWinEH.cpp \
  $(llvm_lib_mc_DIR)/MachObjectWriter.cpp \
  $(llvm_lib_mc_DIR)/StringTableBuilder.cpp \
  $(llvm_lib_mc_DIR)/SubtargetFeature.cpp \
  $(llvm_lib_mc_DIR)/WasmObjectWriter.cpp \
  $(llvm_lib_mc_DIR)/WinCOFFObjectWriter.cpp



llvm_lib_mcdisassembler_DIR=$(LLVM_ROOT_PATH)/lib/MC/MCDisassembler

llvm_lib_mcdisassembler_SRC_FILES:= \
  $(llvm_lib_mcdisassembler_DIR)/Disassembler.cpp \
  $(llvm_lib_mcdisassembler_DIR)/MCDisassembler.cpp \
  $(llvm_lib_mcdisassembler_DIR)/MCExternalSymbolizer.cpp \
  $(llvm_lib_mcdisassembler_DIR)/MCRelocationInfo.cpp \
  $(llvm_lib_mcdisassembler_DIR)/MCSymbolizer.cpp



llvm_lib_mcparser_DIR=$(LLVM_ROOT_PATH)/lib/MC/MCParser

llvm_lib_mcparser_SRC_FILES:= \
  $(llvm_lib_mcparser_DIR)/AsmLexer.cpp \
  $(llvm_lib_mcparser_DIR)/AsmParser.cpp \
  $(llvm_lib_mcparser_DIR)/COFFAsmParser.cpp \
  $(llvm_lib_mcparser_DIR)/DarwinAsmParser.cpp \
  $(llvm_lib_mcparser_DIR)/ELFAsmParser.cpp \
  $(llvm_lib_mcparser_DIR)/MCAsmLexer.cpp \
  $(llvm_lib_mcparser_DIR)/MCAsmParser.cpp \
  $(llvm_lib_mcparser_DIR)/MCAsmParserExtension.cpp \
  $(llvm_lib_mcparser_DIR)/MCTargetAsmParser.cpp



llvm_lib_objcarcopts_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/ObjCARC

llvm_lib_objcarcopts_SRC_FILES:= \
  $(llvm_lib_objcarcopts_DIR)/DependencyAnalysis.cpp \
  $(llvm_lib_objcarcopts_DIR)/ObjCARC.cpp \
  $(llvm_lib_objcarcopts_DIR)/ObjCARCAPElim.cpp \
  $(llvm_lib_objcarcopts_DIR)/ObjCARCContract.cpp \
  $(llvm_lib_objcarcopts_DIR)/ObjCARCExpand.cpp \
  $(llvm_lib_objcarcopts_DIR)/ObjCARCOpts.cpp \
  $(llvm_lib_objcarcopts_DIR)/ProvenanceAnalysis.cpp \
  $(llvm_lib_objcarcopts_DIR)/ProvenanceAnalysisEvaluator.cpp \
  $(llvm_lib_objcarcopts_DIR)/PtrState.cpp



llvm_lib_object_DIR=$(LLVM_ROOT_PATH)/lib/Object

llvm_lib_object_SRC_FILES:= \
  $(llvm_lib_object_DIR)/Archive.cpp \
  $(llvm_lib_object_DIR)/ArchiveWriter.cpp \
  $(llvm_lib_object_DIR)/Binary.cpp \
  $(llvm_lib_object_DIR)/COFFImportFile.cpp \
  $(llvm_lib_object_DIR)/COFFModuleDefinition.cpp \
  $(llvm_lib_object_DIR)/COFFObjectFile.cpp \
  $(llvm_lib_object_DIR)/Decompressor.cpp \
  $(llvm_lib_object_DIR)/ELF.cpp \
  $(llvm_lib_object_DIR)/ELFObjectFile.cpp \
  $(llvm_lib_object_DIR)/Error.cpp \
  $(llvm_lib_object_DIR)/IRObjectFile.cpp \
  $(llvm_lib_object_DIR)/IRSymtab.cpp \
  $(llvm_lib_object_DIR)/MachOObjectFile.cpp \
  $(llvm_lib_object_DIR)/MachOUniversal.cpp \
  $(llvm_lib_object_DIR)/ModuleSymbolTable.cpp \
  $(llvm_lib_object_DIR)/Object.cpp \
  $(llvm_lib_object_DIR)/ObjectFile.cpp \
  $(llvm_lib_object_DIR)/RecordStreamer.cpp \
  $(llvm_lib_object_DIR)/SymbolSize.cpp \
  $(llvm_lib_object_DIR)/SymbolicFile.cpp \
  $(llvm_lib_object_DIR)/WasmObjectFile.cpp \
  $(llvm_lib_object_DIR)/WindowsResource.cpp



llvm_lib_option_DIR=$(LLVM_ROOT_PATH)/lib/Option

llvm_lib_option_SRC_FILES:= \
  $(llvm_lib_option_DIR)/Arg.cpp \
  $(llvm_lib_option_DIR)/ArgList.cpp \
  $(llvm_lib_option_DIR)/OptTable.cpp \
  $(llvm_lib_option_DIR)/Option.cpp



llvm_lib_passes_DIR=$(LLVM_ROOT_PATH)/lib/Passes

llvm_lib_passes_SRC_FILES:= \
  $(llvm_lib_passes_DIR)/PassBuilder.cpp \
  $(llvm_lib_passes_DIR)/PassPlugin.cpp



llvm_lib_profiledata_DIR=$(LLVM_ROOT_PATH)/lib/ProfileData

llvm_lib_profiledata_SRC_FILES:= \
  $(llvm_lib_profiledata_DIR)/GCOV.cpp \
  $(llvm_lib_profiledata_DIR)/InstrProf.cpp \
  $(llvm_lib_profiledata_DIR)/InstrProfReader.cpp \
  $(llvm_lib_profiledata_DIR)/InstrProfWriter.cpp \
  $(llvm_lib_profiledata_DIR)/ProfileSummaryBuilder.cpp \
  $(llvm_lib_profiledata_DIR)/SampleProf.cpp \
  $(llvm_lib_profiledata_DIR)/SampleProfReader.cpp \
  $(llvm_lib_profiledata_DIR)/SampleProfWriter.cpp



llvm_lib_scalaropts_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/Scalar

llvm_lib_scalaropts_SRC_FILES:= \
  $(llvm_lib_scalaropts_DIR)/ADCE.cpp \
  $(llvm_lib_scalaropts_DIR)/AlignmentFromAssumptions.cpp \
  $(llvm_lib_scalaropts_DIR)/BDCE.cpp \
  $(llvm_lib_scalaropts_DIR)/CallSiteSplitting.cpp \
  $(llvm_lib_scalaropts_DIR)/ConstantHoisting.cpp \
  $(llvm_lib_scalaropts_DIR)/ConstantProp.cpp \
  $(llvm_lib_scalaropts_DIR)/CorrelatedValuePropagation.cpp \
  $(llvm_lib_scalaropts_DIR)/DCE.cpp \
  $(llvm_lib_scalaropts_DIR)/DeadStoreElimination.cpp \
  $(llvm_lib_scalaropts_DIR)/DivRemPairs.cpp \
  $(llvm_lib_scalaropts_DIR)/EarlyCSE.cpp \
  $(llvm_lib_scalaropts_DIR)/FlattenCFGPass.cpp \
  $(llvm_lib_scalaropts_DIR)/Float2Int.cpp \
  $(llvm_lib_scalaropts_DIR)/GVN.cpp \
  $(llvm_lib_scalaropts_DIR)/GVNHoist.cpp \
  $(llvm_lib_scalaropts_DIR)/GVNSink.cpp \
  $(llvm_lib_scalaropts_DIR)/GuardWidening.cpp \
  $(llvm_lib_scalaropts_DIR)/IVUsersPrinter.cpp \
  $(llvm_lib_scalaropts_DIR)/IndVarSimplify.cpp \
  $(llvm_lib_scalaropts_DIR)/InductiveRangeCheckElimination.cpp \
  $(llvm_lib_scalaropts_DIR)/InferAddressSpaces.cpp \
  $(llvm_lib_scalaropts_DIR)/InstSimplifyPass.cpp \
  $(llvm_lib_scalaropts_DIR)/JumpThreading.cpp \
  $(llvm_lib_scalaropts_DIR)/LICM.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopAccessAnalysisPrinter.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopDataPrefetch.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopDeletion.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopDistribute.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopIdiomRecognize.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopInstSimplify.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopInterchange.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopLoadElimination.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopPassManager.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopPredication.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopRerollPass.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopRotation.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopSimplifyCFG.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopSink.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopStrengthReduce.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopUnrollAndJamPass.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopUnrollPass.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopUnswitch.cpp \
  $(llvm_lib_scalaropts_DIR)/LoopVersioningLICM.cpp \
  $(llvm_lib_scalaropts_DIR)/LowerAtomic.cpp \
  $(llvm_lib_scalaropts_DIR)/LowerExpectIntrinsic.cpp \
  $(llvm_lib_scalaropts_DIR)/LowerGuardIntrinsic.cpp \
  $(llvm_lib_scalaropts_DIR)/MemCpyOptimizer.cpp \
  $(llvm_lib_scalaropts_DIR)/MergeICmps.cpp \
  $(llvm_lib_scalaropts_DIR)/MergedLoadStoreMotion.cpp \
  $(llvm_lib_scalaropts_DIR)/NaryReassociate.cpp \
  $(llvm_lib_scalaropts_DIR)/NewGVN.cpp \
  $(llvm_lib_scalaropts_DIR)/PartiallyInlineLibCalls.cpp \
  $(llvm_lib_scalaropts_DIR)/PlaceSafepoints.cpp \
  $(llvm_lib_scalaropts_DIR)/Reassociate.cpp \
  $(llvm_lib_scalaropts_DIR)/Reg2Mem.cpp \
  $(llvm_lib_scalaropts_DIR)/RewriteStatepointsForGC.cpp \
  $(llvm_lib_scalaropts_DIR)/SCCP.cpp \
  $(llvm_lib_scalaropts_DIR)/SROA.cpp \
  $(llvm_lib_scalaropts_DIR)/Scalar.cpp \
  $(llvm_lib_scalaropts_DIR)/Scalarizer.cpp \
  $(llvm_lib_scalaropts_DIR)/SeparateConstOffsetFromGEP.cpp \
  $(llvm_lib_scalaropts_DIR)/SimpleLoopUnswitch.cpp \
  $(llvm_lib_scalaropts_DIR)/SimplifyCFGPass.cpp \
  $(llvm_lib_scalaropts_DIR)/Sink.cpp \
  $(llvm_lib_scalaropts_DIR)/SpeculateAroundPHIs.cpp \
  $(llvm_lib_scalaropts_DIR)/SpeculativeExecution.cpp \
  $(llvm_lib_scalaropts_DIR)/StraightLineStrengthReduce.cpp \
  $(llvm_lib_scalaropts_DIR)/StructurizeCFG.cpp \
  $(llvm_lib_scalaropts_DIR)/TailRecursionElimination.cpp



llvm_lib_selectiondag_DIR=$(LLVM_ROOT_PATH)/lib/CodeGen/SelectionDAG

llvm_lib_selectiondag_SRC_FILES:= \
  $(llvm_lib_selectiondag_DIR)/DAGCombiner.cpp \
  $(llvm_lib_selectiondag_DIR)/FastISel.cpp \
  $(llvm_lib_selectiondag_DIR)/FunctionLoweringInfo.cpp \
  $(llvm_lib_selectiondag_DIR)/InstrEmitter.cpp \
  $(llvm_lib_selectiondag_DIR)/LegalizeDAG.cpp \
  $(llvm_lib_selectiondag_DIR)/LegalizeFloatTypes.cpp \
  $(llvm_lib_selectiondag_DIR)/LegalizeIntegerTypes.cpp \
  $(llvm_lib_selectiondag_DIR)/LegalizeTypes.cpp \
  $(llvm_lib_selectiondag_DIR)/LegalizeTypesGeneric.cpp \
  $(llvm_lib_selectiondag_DIR)/LegalizeVectorOps.cpp \
  $(llvm_lib_selectiondag_DIR)/LegalizeVectorTypes.cpp \
  $(llvm_lib_selectiondag_DIR)/ResourcePriorityQueue.cpp \
  $(llvm_lib_selectiondag_DIR)/ScheduleDAGFast.cpp \
  $(llvm_lib_selectiondag_DIR)/ScheduleDAGRRList.cpp \
  $(llvm_lib_selectiondag_DIR)/ScheduleDAGSDNodes.cpp \
  $(llvm_lib_selectiondag_DIR)/ScheduleDAGVLIW.cpp \
  $(llvm_lib_selectiondag_DIR)/SelectionDAG.cpp \
  $(llvm_lib_selectiondag_DIR)/SelectionDAGAddressAnalysis.cpp \
  $(llvm_lib_selectiondag_DIR)/SelectionDAGBuilder.cpp \
  $(llvm_lib_selectiondag_DIR)/SelectionDAGDumper.cpp \
  $(llvm_lib_selectiondag_DIR)/SelectionDAGISel.cpp \
  $(llvm_lib_selectiondag_DIR)/SelectionDAGPrinter.cpp \
  $(llvm_lib_selectiondag_DIR)/SelectionDAGTargetInfo.cpp \
  $(llvm_lib_selectiondag_DIR)/StatepointLowering.cpp \
  $(llvm_lib_selectiondag_DIR)/TargetLowering.cpp



llvm_lib_support_DIR=$(LLVM_ROOT_PATH)/lib/Support

llvm_lib_support_SRC_FILES:= \
  $(llvm_lib_support_DIR)/AMDGPUMetadata.cpp \
  $(llvm_lib_support_DIR)/APFloat.cpp \
  $(llvm_lib_support_DIR)/APInt.cpp \
  $(llvm_lib_support_DIR)/APSInt.cpp \
  $(llvm_lib_support_DIR)/ARMAttributeParser.cpp \
  $(llvm_lib_support_DIR)/ARMBuildAttrs.cpp \
  $(llvm_lib_support_DIR)/ARMWinEH.cpp \
  $(llvm_lib_support_DIR)/Allocator.cpp \
  $(llvm_lib_support_DIR)/Atomic.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamError.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamReader.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamRef.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamWriter.cpp \
  $(llvm_lib_support_DIR)/BlockFrequency.cpp \
  $(llvm_lib_support_DIR)/BranchProbability.cpp \
  $(llvm_lib_support_DIR)/COM.cpp \
  $(llvm_lib_support_DIR)/CachePruning.cpp \
  $(llvm_lib_support_DIR)/Chrono.cpp \
  $(llvm_lib_support_DIR)/CodeGenCoverage.cpp \
  $(llvm_lib_support_DIR)/CommandLine.cpp \
  $(llvm_lib_support_DIR)/Compression.cpp \
  $(llvm_lib_support_DIR)/ConvertUTF.cpp \
  $(llvm_lib_support_DIR)/ConvertUTFWrapper.cpp \
  $(llvm_lib_support_DIR)/CrashRecoveryContext.cpp \
  $(llvm_lib_support_DIR)/DAGDeltaAlgorithm.cpp \
  $(llvm_lib_support_DIR)/DJB.cpp \
  $(llvm_lib_support_DIR)/DataExtractor.cpp \
  $(llvm_lib_support_DIR)/Debug.cpp \
  $(llvm_lib_support_DIR)/DebugCounter.cpp \
  $(llvm_lib_support_DIR)/DeltaAlgorithm.cpp \
  $(llvm_lib_support_DIR)/DynamicLibrary.cpp \
  $(llvm_lib_support_DIR)/Errno.cpp \
  $(llvm_lib_support_DIR)/Error.cpp \
  $(llvm_lib_support_DIR)/ErrorHandling.cpp \
  $(llvm_lib_support_DIR)/FileOutputBuffer.cpp \
  $(llvm_lib_support_DIR)/FileUtilities.cpp \
  $(llvm_lib_support_DIR)/FoldingSet.cpp \
  $(llvm_lib_support_DIR)/FormatVariadic.cpp \
  $(llvm_lib_support_DIR)/FormattedStream.cpp \
  $(llvm_lib_support_DIR)/GlobPattern.cpp \
  $(llvm_lib_support_DIR)/GraphWriter.cpp \
  $(llvm_lib_support_DIR)/Hashing.cpp \
  $(llvm_lib_support_DIR)/Host.cpp \
  $(llvm_lib_support_DIR)/InitLLVM.cpp \
  $(llvm_lib_support_DIR)/IntEqClasses.cpp \
  $(llvm_lib_support_DIR)/IntervalMap.cpp \
  $(llvm_lib_support_DIR)/JSON.cpp \
  $(llvm_lib_support_DIR)/JamCRC.cpp \
  $(llvm_lib_support_DIR)/KnownBits.cpp \
  $(llvm_lib_support_DIR)/LEB128.cpp \
  $(llvm_lib_support_DIR)/LineIterator.cpp \
  $(llvm_lib_support_DIR)/Locale.cpp \
  $(llvm_lib_support_DIR)/LockFileManager.cpp \
  $(llvm_lib_support_DIR)/LowLevelType.cpp \
  $(llvm_lib_support_DIR)/MD5.cpp \
  $(llvm_lib_support_DIR)/ManagedStatic.cpp \
  $(llvm_lib_support_DIR)/MathExtras.cpp \
  $(llvm_lib_support_DIR)/Memory.cpp \
  $(llvm_lib_support_DIR)/MemoryBuffer.cpp \
  $(llvm_lib_support_DIR)/Mutex.cpp \
  $(llvm_lib_support_DIR)/NativeFormatting.cpp \
  $(llvm_lib_support_DIR)/Options.cpp \
  $(llvm_lib_support_DIR)/Parallel.cpp \
  $(llvm_lib_support_DIR)/Path.cpp \
  $(llvm_lib_support_DIR)/PluginLoader.cpp \
  $(llvm_lib_support_DIR)/PrettyStackTrace.cpp \
  $(llvm_lib_support_DIR)/Process.cpp \
  $(llvm_lib_support_DIR)/Program.cpp \
  $(llvm_lib_support_DIR)/RWMutex.cpp \
  $(llvm_lib_support_DIR)/RandomNumberGenerator.cpp \
  $(llvm_lib_support_DIR)/Regex.cpp \
  $(llvm_lib_support_DIR)/SHA1.cpp \
  $(llvm_lib_support_DIR)/ScaledNumber.cpp \
  $(llvm_lib_support_DIR)/ScopedPrinter.cpp \
  $(llvm_lib_support_DIR)/Signals.cpp \
  $(llvm_lib_support_DIR)/SmallPtrSet.cpp \
  $(llvm_lib_support_DIR)/SmallVector.cpp \
  $(llvm_lib_support_DIR)/SourceMgr.cpp \
  $(llvm_lib_support_DIR)/SpecialCaseList.cpp \
  $(llvm_lib_support_DIR)/Statistic.cpp \
  $(llvm_lib_support_DIR)/StringExtras.cpp \
  $(llvm_lib_support_DIR)/StringMap.cpp \
  $(llvm_lib_support_DIR)/StringPool.cpp \
  $(llvm_lib_support_DIR)/StringRef.cpp \
  $(llvm_lib_support_DIR)/StringSaver.cpp \
  $(llvm_lib_support_DIR)/SystemUtils.cpp \
  $(llvm_lib_support_DIR)/TarWriter.cpp \
  $(llvm_lib_support_DIR)/TargetParser.cpp \
  $(llvm_lib_support_DIR)/TargetRegistry.cpp \
  $(llvm_lib_support_DIR)/ThreadLocal.cpp \
  $(llvm_lib_support_DIR)/ThreadPool.cpp \
  $(llvm_lib_support_DIR)/Threading.cpp \
  $(llvm_lib_support_DIR)/Timer.cpp \
  $(llvm_lib_support_DIR)/ToolOutputFile.cpp \
  $(llvm_lib_support_DIR)/TrigramIndex.cpp \
  $(llvm_lib_support_DIR)/Triple.cpp \
  $(llvm_lib_support_DIR)/Twine.cpp \
  $(llvm_lib_support_DIR)/Unicode.cpp \
  $(llvm_lib_support_DIR)/UnicodeCaseFold.cpp \
  $(llvm_lib_support_DIR)/Valgrind.cpp \
  $(llvm_lib_support_DIR)/VersionTuple.cpp \
  $(llvm_lib_support_DIR)/Watchdog.cpp \
  $(llvm_lib_support_DIR)/WithColor.cpp \
  $(llvm_lib_support_DIR)/YAMLParser.cpp \
  $(llvm_lib_support_DIR)/YAMLTraits.cpp \
  $(llvm_lib_support_DIR)/circular_raw_ostream.cpp \
  $(llvm_lib_support_DIR)/raw_os_ostream.cpp \
  $(llvm_lib_support_DIR)/raw_ostream.cpp \
  $(llvm_lib_support_DIR)/regcomp.c \
  $(llvm_lib_support_DIR)/regerror.c \
  $(llvm_lib_support_DIR)/regexec.c \
  $(llvm_lib_support_DIR)/regfree.c \
  $(llvm_lib_support_DIR)/regstrlcpy.c \
  $(llvm_lib_support_DIR)/xxhash.cpp



llvm_lib_target_DIR=$(LLVM_ROOT_PATH)/lib/Target

llvm_lib_target_SRC_FILES:= \
  $(llvm_lib_target_DIR)/Target.cpp \
  $(llvm_lib_target_DIR)/TargetIntrinsicInfo.cpp \
  $(llvm_lib_target_DIR)/TargetLoweringObjectFile.cpp \
  $(llvm_lib_target_DIR)/TargetMachine.cpp \
  $(llvm_lib_target_DIR)/TargetMachineC.cpp



llvm_lib_transformutils_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/Utils

llvm_lib_transformutils_SRC_FILES:= \
  $(llvm_lib_transformutils_DIR)/ASanStackFrameLayout.cpp \
  $(llvm_lib_transformutils_DIR)/AddDiscriminators.cpp \
  $(llvm_lib_transformutils_DIR)/BasicBlockUtils.cpp \
  $(llvm_lib_transformutils_DIR)/BreakCriticalEdges.cpp \
  $(llvm_lib_transformutils_DIR)/BuildLibCalls.cpp \
  $(llvm_lib_transformutils_DIR)/BypassSlowDivision.cpp \
  $(llvm_lib_transformutils_DIR)/CallPromotionUtils.cpp \
  $(llvm_lib_transformutils_DIR)/CloneFunction.cpp \
  $(llvm_lib_transformutils_DIR)/CloneModule.cpp \
  $(llvm_lib_transformutils_DIR)/CodeExtractor.cpp \
  $(llvm_lib_transformutils_DIR)/CtorUtils.cpp \
  $(llvm_lib_transformutils_DIR)/DemoteRegToStack.cpp \
  $(llvm_lib_transformutils_DIR)/EntryExitInstrumenter.cpp \
  $(llvm_lib_transformutils_DIR)/EscapeEnumerator.cpp \
  $(llvm_lib_transformutils_DIR)/Evaluator.cpp \
  $(llvm_lib_transformutils_DIR)/FlattenCFG.cpp \
  $(llvm_lib_transformutils_DIR)/FunctionComparator.cpp \
  $(llvm_lib_transformutils_DIR)/FunctionImportUtils.cpp \
  $(llvm_lib_transformutils_DIR)/GlobalStatus.cpp \
  $(llvm_lib_transformutils_DIR)/ImportedFunctionsInliningStatistics.cpp \
  $(llvm_lib_transformutils_DIR)/InlineFunction.cpp \
  $(llvm_lib_transformutils_DIR)/InstructionNamer.cpp \
  $(llvm_lib_transformutils_DIR)/IntegerDivision.cpp \
  $(llvm_lib_transformutils_DIR)/LCSSA.cpp \
  $(llvm_lib_transformutils_DIR)/LibCallsShrinkWrap.cpp \
  $(llvm_lib_transformutils_DIR)/Local.cpp \
  $(llvm_lib_transformutils_DIR)/LoopRotationUtils.cpp \
  $(llvm_lib_transformutils_DIR)/LoopSimplify.cpp \
  $(llvm_lib_transformutils_DIR)/LoopUnroll.cpp \
  $(llvm_lib_transformutils_DIR)/LoopUnrollAndJam.cpp \
  $(llvm_lib_transformutils_DIR)/LoopUnrollPeel.cpp \
  $(llvm_lib_transformutils_DIR)/LoopUnrollRuntime.cpp \
  $(llvm_lib_transformutils_DIR)/LoopUtils.cpp \
  $(llvm_lib_transformutils_DIR)/LoopVersioning.cpp \
  $(llvm_lib_transformutils_DIR)/LowerInvoke.cpp \
  $(llvm_lib_transformutils_DIR)/LowerMemIntrinsics.cpp \
  $(llvm_lib_transformutils_DIR)/LowerSwitch.cpp \
  $(llvm_lib_transformutils_DIR)/Mem2Reg.cpp \
  $(llvm_lib_transformutils_DIR)/MetaRenamer.cpp \
  $(llvm_lib_transformutils_DIR)/ModuleUtils.cpp \
  $(llvm_lib_transformutils_DIR)/NameAnonGlobals.cpp \
  $(llvm_lib_transformutils_DIR)/OrderedInstructions.cpp \
  $(llvm_lib_transformutils_DIR)/PredicateInfo.cpp \
  $(llvm_lib_transformutils_DIR)/PromoteMemoryToRegister.cpp \
  $(llvm_lib_transformutils_DIR)/SSAUpdater.cpp \
  $(llvm_lib_transformutils_DIR)/SSAUpdaterBulk.cpp \
  $(llvm_lib_transformutils_DIR)/SanitizerStats.cpp \
  $(llvm_lib_transformutils_DIR)/SimplifyCFG.cpp \
  $(llvm_lib_transformutils_DIR)/SimplifyIndVar.cpp \
  $(llvm_lib_transformutils_DIR)/SimplifyLibCalls.cpp \
  $(llvm_lib_transformutils_DIR)/SplitModule.cpp \
  $(llvm_lib_transformutils_DIR)/StripGCRelocates.cpp \
  $(llvm_lib_transformutils_DIR)/StripNonLineTableDebugInfo.cpp \
  $(llvm_lib_transformutils_DIR)/SymbolRewriter.cpp \
  $(llvm_lib_transformutils_DIR)/UnifyFunctionExitNodes.cpp \
  $(llvm_lib_transformutils_DIR)/Utils.cpp \
  $(llvm_lib_transformutils_DIR)/VNCoercion.cpp \
  $(llvm_lib_transformutils_DIR)/ValueMapper.cpp



llvm_lib_vectorize_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/Vectorize

llvm_lib_vectorize_SRC_FILES:= \
  $(llvm_lib_vectorize_DIR)/LoadStoreVectorizer.cpp \
  $(llvm_lib_vectorize_DIR)/LoopVectorizationLegality.cpp \
  $(llvm_lib_vectorize_DIR)/LoopVectorize.cpp \
  $(llvm_lib_vectorize_DIR)/SLPVectorizer.cpp \
  $(llvm_lib_vectorize_DIR)/VPlan.cpp \
  $(llvm_lib_vectorize_DIR)/VPlanHCFGBuilder.cpp \
  $(llvm_lib_vectorize_DIR)/VPlanHCFGTransforms.cpp \
  $(llvm_lib_vectorize_DIR)/VPlanVerifier.cpp \
  $(llvm_lib_vectorize_DIR)/Vectorize.cpp



llvm_lib_webassemblyasmparser_DIR=$(LLVM_ROOT_PATH)/lib/Target/WebAssembly/AsmParser

llvm_lib_webassemblyasmparser_SRC_FILES:= \
  $(llvm_lib_webassemblyasmparser_DIR)/WebAssemblyAsmParser.cpp



llvm_lib_webassemblyasmprinter_DIR=$(LLVM_ROOT_PATH)/lib/Target/WebAssembly/InstPrinter

llvm_lib_webassemblyasmprinter_SRC_FILES:= \
  $(llvm_lib_webassemblyasmprinter_DIR)/WebAssemblyInstPrinter.cpp



llvm_lib_webassemblycodegen_DIR=$(LLVM_ROOT_PATH)/lib/Target/WebAssembly

llvm_lib_webassemblycodegen_SRC_FILES:= \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyAddMissingPrototypes.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyArgumentMove.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyAsmPrinter.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyCFGSort.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyCFGStackify.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyCallIndirectFixup.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyExceptionInfo.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyExplicitLocals.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyFastISel.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyFixFunctionBitcasts.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyFixIrreducibleControlFlow.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyFrameLowering.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyISelDAGToDAG.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyISelLowering.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyInstrInfo.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyLateEHPrepare.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyLowerBrUnless.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyLowerEmscriptenEHSjLj.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyLowerGlobalDtors.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyMCInstLower.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyMachineFunctionInfo.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyOptimizeLiveIntervals.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyOptimizeReturned.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyPeephole.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyPrepareForLiveIntervals.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyRegColoring.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyRegNumbering.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyRegStackify.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyRegisterInfo.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyReplacePhysRegs.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyRuntimeLibcallSignatures.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblySelectionDAGInfo.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblySetP2AlignOperands.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyStoreResults.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblySubtarget.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyTargetMachine.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyTargetObjectFile.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyTargetTransformInfo.cpp \
  $(llvm_lib_webassemblycodegen_DIR)/WebAssemblyUtilities.cpp



llvm_lib_webassemblydesc_DIR=$(LLVM_ROOT_PATH)/lib/Target/WebAssembly/MCTargetDesc

llvm_lib_webassemblydesc_SRC_FILES:= \
  $(llvm_lib_webassemblydesc_DIR)/WebAssemblyAsmBackend.cpp \
  $(llvm_lib_webassemblydesc_DIR)/WebAssemblyMCAsmInfo.cpp \
  $(llvm_lib_webassemblydesc_DIR)/WebAssemblyMCCodeEmitter.cpp \
  $(llvm_lib_webassemblydesc_DIR)/WebAssemblyMCTargetDesc.cpp \
  $(llvm_lib_webassemblydesc_DIR)/WebAssemblyTargetStreamer.cpp \
  $(llvm_lib_webassemblydesc_DIR)/WebAssemblyWasmObjectWriter.cpp



llvm_lib_webassemblydisassembler_DIR=$(LLVM_ROOT_PATH)/lib/Target/WebAssembly/Disassembler

llvm_lib_webassemblydisassembler_SRC_FILES:= \
  $(llvm_lib_webassemblydisassembler_DIR)/WebAssemblyDisassembler.cpp



llvm_lib_webassemblyinfo_DIR=$(LLVM_ROOT_PATH)/lib/Target/WebAssembly/TargetInfo

llvm_lib_webassemblyinfo_SRC_FILES:= \
  $(llvm_lib_webassemblyinfo_DIR)/WebAssemblyTargetInfo.cpp



llvm_lib_windowsmanifest_DIR=$(LLVM_ROOT_PATH)/lib/WindowsManifest

llvm_lib_windowsmanifest_SRC_FILES:= \
  $(llvm_lib_windowsmanifest_DIR)/WindowsManifestMerger.cpp



llvm_lib_x86asmparser_DIR=$(LLVM_ROOT_PATH)/lib/Target/X86/AsmParser

llvm_lib_x86asmparser_SRC_FILES:= \
  $(llvm_lib_x86asmparser_DIR)/X86AsmInstrumentation.cpp \
  $(llvm_lib_x86asmparser_DIR)/X86AsmParser.cpp



llvm_lib_x86asmprinter_DIR=$(LLVM_ROOT_PATH)/lib/Target/X86/InstPrinter

llvm_lib_x86asmprinter_SRC_FILES:= \
  $(llvm_lib_x86asmprinter_DIR)/X86ATTInstPrinter.cpp \
  $(llvm_lib_x86asmprinter_DIR)/X86InstComments.cpp \
  $(llvm_lib_x86asmprinter_DIR)/X86InstPrinterCommon.cpp \
  $(llvm_lib_x86asmprinter_DIR)/X86IntelInstPrinter.cpp



llvm_lib_x86codegen_DIR=$(LLVM_ROOT_PATH)/lib/Target/X86

llvm_lib_x86codegen_SRC_FILES:= \
  $(llvm_lib_x86codegen_DIR)/ShadowCallStack.cpp \
  $(llvm_lib_x86codegen_DIR)/X86AsmPrinter.cpp \
  $(llvm_lib_x86codegen_DIR)/X86AvoidStoreForwardingBlocks.cpp \
  $(llvm_lib_x86codegen_DIR)/X86CallFrameOptimization.cpp \
  $(llvm_lib_x86codegen_DIR)/X86CallLowering.cpp \
  $(llvm_lib_x86codegen_DIR)/X86CallingConv.cpp \
  $(llvm_lib_x86codegen_DIR)/X86CmovConversion.cpp \
  $(llvm_lib_x86codegen_DIR)/X86DomainReassignment.cpp \
  $(llvm_lib_x86codegen_DIR)/X86EvexToVex.cpp \
  $(llvm_lib_x86codegen_DIR)/X86ExpandPseudo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86FastISel.cpp \
  $(llvm_lib_x86codegen_DIR)/X86FixupBWInsts.cpp \
  $(llvm_lib_x86codegen_DIR)/X86FixupLEAs.cpp \
  $(llvm_lib_x86codegen_DIR)/X86FixupSetCC.cpp \
  $(llvm_lib_x86codegen_DIR)/X86FlagsCopyLowering.cpp \
  $(llvm_lib_x86codegen_DIR)/X86FloatingPoint.cpp \
  $(llvm_lib_x86codegen_DIR)/X86FrameLowering.cpp \
  $(llvm_lib_x86codegen_DIR)/X86ISelDAGToDAG.cpp \
  $(llvm_lib_x86codegen_DIR)/X86ISelLowering.cpp \
  $(llvm_lib_x86codegen_DIR)/X86IndirectBranchTracking.cpp \
  $(llvm_lib_x86codegen_DIR)/X86InstrFMA3Info.cpp \
  $(llvm_lib_x86codegen_DIR)/X86InstrFoldTables.cpp \
  $(llvm_lib_x86codegen_DIR)/X86InstrInfo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86InstructionSelector.cpp \
  $(llvm_lib_x86codegen_DIR)/X86InterleavedAccess.cpp \
  $(llvm_lib_x86codegen_DIR)/X86LegalizerInfo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86MCInstLower.cpp \
  $(llvm_lib_x86codegen_DIR)/X86MachineFunctionInfo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86MacroFusion.cpp \
  $(llvm_lib_x86codegen_DIR)/X86OptimizeLEAs.cpp \
  $(llvm_lib_x86codegen_DIR)/X86PadShortFunction.cpp \
  $(llvm_lib_x86codegen_DIR)/X86RegisterBankInfo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86RegisterInfo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86RetpolineThunks.cpp \
  $(llvm_lib_x86codegen_DIR)/X86SelectionDAGInfo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86ShuffleDecodeConstantPool.cpp \
  $(llvm_lib_x86codegen_DIR)/X86SpeculativeLoadHardening.cpp \
  $(llvm_lib_x86codegen_DIR)/X86Subtarget.cpp \
  $(llvm_lib_x86codegen_DIR)/X86TargetMachine.cpp \
  $(llvm_lib_x86codegen_DIR)/X86TargetObjectFile.cpp \
  $(llvm_lib_x86codegen_DIR)/X86TargetTransformInfo.cpp \
  $(llvm_lib_x86codegen_DIR)/X86VZeroUpper.cpp \
  $(llvm_lib_x86codegen_DIR)/X86WinAllocaExpander.cpp \
  $(llvm_lib_x86codegen_DIR)/X86WinEHState.cpp



llvm_lib_x86desc_DIR=$(LLVM_ROOT_PATH)/lib/Target/X86/MCTargetDesc

llvm_lib_x86desc_SRC_FILES:= \
  $(llvm_lib_x86desc_DIR)/X86AsmBackend.cpp \
  $(llvm_lib_x86desc_DIR)/X86ELFObjectWriter.cpp \
  $(llvm_lib_x86desc_DIR)/X86MCAsmInfo.cpp \
  $(llvm_lib_x86desc_DIR)/X86MCCodeEmitter.cpp \
  $(llvm_lib_x86desc_DIR)/X86MCTargetDesc.cpp \
  $(llvm_lib_x86desc_DIR)/X86MachObjectWriter.cpp \
  $(llvm_lib_x86desc_DIR)/X86WinCOFFObjectWriter.cpp \
  $(llvm_lib_x86desc_DIR)/X86WinCOFFStreamer.cpp \
  $(llvm_lib_x86desc_DIR)/X86WinCOFFTargetStreamer.cpp



llvm_lib_x86disassembler_DIR=$(LLVM_ROOT_PATH)/lib/Target/X86/Disassembler

llvm_lib_x86disassembler_SRC_FILES:= \
  $(llvm_lib_x86disassembler_DIR)/X86Disassembler.cpp \
  $(llvm_lib_x86disassembler_DIR)/X86DisassemblerDecoder.cpp



llvm_lib_x86info_DIR=$(LLVM_ROOT_PATH)/lib/Target/X86/TargetInfo

llvm_lib_x86info_SRC_FILES:= \
  $(llvm_lib_x86info_DIR)/X86TargetInfo.cpp



llvm_lib_x86utils_DIR=$(LLVM_ROOT_PATH)/lib/Target/X86/Utils

llvm_lib_x86utils_SRC_FILES:= \
  $(llvm_lib_x86utils_DIR)/X86ShuffleDecode.cpp


